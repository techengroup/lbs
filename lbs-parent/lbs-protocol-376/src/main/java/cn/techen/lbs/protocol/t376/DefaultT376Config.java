package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractConfig;

public class DefaultT376Config extends AbstractConfig implements T376Config {

	private String commAddr;
	private DIR dir;
	private int prm;
	private ACD acd;
	private int fcb;
	private int fcv;
	private int func;
	private AFN afn;
	private TPV tpv;
	private int fir;
	private int fin;
	private CON con;
	private int seq;

	@Override
	public String getCommAddr() {
		return commAddr;
	}

	@Override
	public T376Config setCommAddr(String commAddr) {
		this.commAddr = commAddr;
		return this;
	}
	
	@Override
	public DIR getDir() {
		return dir;
	}
	
	@Override
	public DefaultT376Config setDir(DIR dir) {
		this.dir = dir;
		return this;
	}

	@Override
	public int getPrm() {
		return prm;
	}

	@Override
	public T376Config setPrm(int prm) {
		this.prm = prm;
		return this;
	}

	@Override
	public ACD getAcd() {
		return acd;
	}

	@Override
	public T376Config setAcd(ACD acd) {
		this.acd = acd;
		return this;
	}

	@Override
	public int getFcb() {
		return fcb;
	}

	@Override
	public T376Config setFcb(int fcb) {
		this.fcb = fcb;
		return this;
	}

	@Override
	public int getFcv() {
		return fcv;
	}

	@Override
	public T376Config setFcv(int fcv) {
		this.fcv = fcv;
		return this;
	}

	@Override
	public int getFunc() {
		return func;
	}

	@Override
	public T376Config setFunc(int func) {
		this.func = func;
		return this;
	}

	@Override
	public AFN getAfn() {
		return afn;
	}

	@Override
	public T376Config setAfn(AFN afn) {
		this.afn = afn;
		return this;
	}

	@Override
	public TPV getTpv() {
		return tpv;
	}

	@Override
	public T376Config setTpv(TPV tpv) {
		this.tpv = tpv;
		return this;
	}

	@Override
	public int getFir() {
		return fir;
	}

	@Override
	public T376Config setFir(int fir) {
		this.fir = fir;
		return this;
	}

	@Override
	public int getFin() {
		return fin;
	}

	@Override
	public T376Config setFin(int fin) {
		this.fin = fin;
		return this;
	}

	@Override
	public CON getCon() {
		return con;
	}

	@Override
	public T376Config setCon(CON con) {
		this.con = con;
		return this;
	}

	@Override
	public int getSeq() {
		return seq;
	}

	@Override
	public T376Config setSeq(int seq) {
		this.seq = seq;
		return this;
	}
	
}
