package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractConfig;

public class DefaultT645Config extends AbstractConfig implements T645Config {
	
	private Control control;
	private DIR dir;
	private Answer answer;
	private FollowUp followUp;
	private String commAddr;
	
	@Override
	public Control getControl() {
		return control;
	}
	
	@Override
	public DefaultT645Config setControl(Control control) {
		this.control = control;
		return this;
	}
	
	@Override
	public DIR getDir() {
		return dir;
	}
	
	@Override
	public DefaultT645Config setDir(DIR dir) {
		this.dir = dir;
		return this;
	}

	@Override
	public Answer getAnswer() {		
		return answer;
	}

	@Override
	public T645Config setAnswer(Answer flag) {
		this.answer = flag;
		return this;
	}

	@Override
	public FollowUp getFollowUp() {
		return followUp;
	}

	@Override
	public T645Config setFollowUp(FollowUp flag) {
		this.followUp = flag;
		return this;
	}

	@Override
	public String getCommAddr() {
		return commAddr;
	}

	@Override
	public T645Config setCommAddr(String commAddr) {
		this.commAddr = commAddr;
		return this;
	}
}
