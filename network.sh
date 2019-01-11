# ============================================================================= #
# Proceduce Flow:
#
# ping xxx.xxx.xxx
# check LAN1
# check LAN2
# check WAN1(4G)
#
# Internet, LAN First, WAN(4G) second
# ============================================================================== #

#Global variable
url="10.158.2.31"
lan1="enp1s0"
lan2="enp2s0"
wan1="wwp0s29u1u3c2"
wanCOM="/dev/ttyUSB0"
wanAPN="3gnet"
sleeptime="10s"

#Link check
linkC(){
	echo -n "   -[$(date)] [$1] Link detecting..."
	ping -c 3 -w 5 $1 >> /dev/null 2>&1
	if [ $? -eq 0 ]; then 
		echo "connect: yes"
		return 0		
	fi
	echo "connect: no"
	return 1
}

#Route check
routeC(){
	echo "   -[$(date)] [$1] Route detecting..."
	netplan apply
	ip route | grep -i "default" > routed.txt
	while read line
	do
		echo $line | grep -i $1 >> /dev/null
		if [ $? -eq 1 ]; then
			route del default >> /dev/null
			echo "   -[$(date)] Delete $line"
		else
			echo "   -[$(date)] Find $line"
			break
		fi
	done < routed.txt
}

#LAN card check
lanC(){
	echo -n "[L]-[$(date)] [$1] LAN device detecting..."
	ethtool $1 | grep -i "Link detected: yes" >> /dev/null
	if [ $? -eq 0 ]; then
		echo "plugin: yes"
		routeC $1			
		linkC $2
		return $?
	else
		echo "plugin: no"
	fi	
	return 1 
}

#4G card check
wanC(){
	echo -n "[W]-[$(date)] [$1] 4G device detecting..."
	lsusb | grep -i "Huawei" >> /dev/null
	if [ $? -eq 0 ]; then
		echo "plugin: yes"
		dailC $1 $2
		return $?
	else
		echo "plugin: no"
	fi
	return 1
}

#4G modem dail check
dailC(){
	local rc=1
	stty -F $wanCOM raw speed 9600 min 0 time 20 >> /dev/null
	echo -e "AT^NDISSTATQRY?\r" > $wanCOM
	cat $wanCOM >> wanLog.txt
	cat wanLog.txt | grep -i "\^NDISSTATQRY: 1,,,\"IPV4\""
	rc=$?
	if [ $rc -eq 1 ]; then
		echo "   -[$(date)] 4G modem...dial:no"
		echo -e "AT+CPIN?\r" > $wanCOM
		cat $wanCOM >> wanLog.txt
		cat wanLog.txt | grep -i "+CPIN: READY"
		if [ $? -eq 1 ]; then
			echo "   -[$(date)] SIM card...plugin:no"
			echo -e "AT^RESET\r" > $wanCOM
			echo "   -[$(date)] 4G modem...reset"
		else
			echo "   -[$(date)] SIM card...plugin:yes"
			echo -e "AT^NDISDUP=1,1,\"$wanAPN\"\r" > $wanCOM
			cat $wanCOM >> wanLog.txt
			cat wanLog.txt | grep -i "\^NDISSTAT: 1,,,\"IPV4\""
			rc=$?
		fi
	fi
	if [ $rc -eq 0 ]; then
		echo "   -[$(date)] 4G modem...dail:yes"
		routeC $1
		linkC $2
		rc=$?
	fi
	echo "" > wanLog.txt
	return $rc
}

#Proceduce Entrance
main(){
	while :
	do
		echo "[M]-[$(date)] Sleep $sleeptime"
		sleep $sleeptime
		linkC $url
		if [ $? -eq 1 ]; then
			lanC $lan1 $url
			if [ $? -eq 1 ]; then
				lanC $lan2 $url
				if [ $? -eq 1 ]; then
					wanC $wan1 $url
				fi
			fi
		fi
	done
}


echo "[M]-[$(date)] Network mornitoring start..."
main

