package cn.techen.lbs.task.networking.manager;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Sector;
import cn.techen.lbs.task.networking.common.Local;
import cn.techen.lbs.task.networking.common.NetContext;

public class StoreHandler extends AbstractHandler {

	public StoreHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {
		Meter meter = context.getMeter();

		if (meter.running().getResult() == 1) {
			success(context, meter);
			context.reset(true);
		} else {
			if (meter.running().retryTimes() >= 2) {
				fail(context, meter);
				context.reset(true);
			} else {
				context.getMeterService().updateFail(meter, false, true);
				context.reset(false);
			}
		}
	}

	private void success(NetContext context, Meter meter) {
		if (meter.getPathtype() == 0) {
			Meter relay = context.getmRelayService().get(meter.getSector(), meter.getGrade(), meter.getDistrictY());
			if (compare(meter, relay)) {
				meter.setRelay(2);
				context.getmRelayService().put(meter);
			}
		} else {
			Meter relay3 = new Meter();
			relay3.setId(meter.getParent());
			relay3.setRelay(3);
			context.getMeterService().updateRelay(relay3);
		}

		context.getMeterService().updateSuccess(meter);
	}

	private void fail(NetContext context, Meter meter) {
		if (meter.getPathtype() == 0 && meter.getFailTimes() == 0) {
			Meter optimalRelay = meetOptimal(context, meter);
			if (optimalRelay != null) {
				optimalRelay.setRelay(1);
				context.getmRelayService().put(optimalRelay);
				context.getMeterService().updateRelay(optimalRelay);
			}
		}

		context.getMeterService().updateFail(meter, true, true);
	}

	private Meter meetOptimal(NetContext context, Meter meter) {
		int s = meter.getSector();
		int g = meter.getGrade();
		int x = meter.getDistrictX();

		Sector sector = context.getMeterService().selectQuantity(s, x);
		int total = sector.getTotal();
		int fail = sector.getFail() + 1;

		if (total <= fail) {
			Meter optimalRelay = optimalRelay(context, s, g);

			if (optimalRelay != null) {
				if (total <= Local.MINFAIL) {
					int ox = optimalRelay.getDistrictX();
					if ((ox - x) > 1) {
						return optimalRelay;
					} else {
						int count = context.getMeterService().selectQuantityAfterX(s, x);
						if (count == 0) {
							return optimalRelay;
						}
					}
				} else {
					return optimalRelay;
				}
			} // 如果optimalRleay为空，应记录错误上报。
		}

		return null;
	}

	private Meter optimalRelay(NetContext context, int sector, int grade) {
		Meter relay0 = context.getmRelayService().get(sector, grade, 0);
		Meter relay1 = context.getmRelayService().get(sector, grade, 1);
		Meter relay2 = context.getmRelayService().get(sector, grade, 2);

		Meter optimalRelay = relay2;

		if (compare(relay0, relay2)) {
			optimalRelay = relay0;
		}
		if (compare(relay1, optimalRelay)) {
			optimalRelay = relay1;
		}

		return optimalRelay;
	}

	private boolean compare(Meter meter, Meter meter0) {
		if (meter == null)
			return false;
		if (meter0 == null)
			return true;

		int districtX = meter.getDistrictX();
		int districtX0 = meter0.getDistrictX();

		if (districtX > districtX0)
			return true;

		if (districtX == districtX0) {
			float factor = caculate(meter.getDistance(), meter.getAngle(), meter.getSignal());
			float factor0 = caculate(meter0.getDistance(), meter0.getAngle(), meter0.getSignal());
			if (factor > factor0)
				return true;
		}

		return false;
	}

	private float caculate(double distance, float angle, int signal) {
		float dFactor = (float) ((distance % Local.DFACTOR) / Local.DFACTOR);
		float sFactor = (float) signal / Local.SFACTOR;
		float aFactor = angle % Local.AFACTOR;

		if (aFactor > Local.APOINT) {
			aFactor = 2 - (aFactor / Local.APOINT);
		} else {
			aFactor = aFactor / Local.APOINT;
		}

		return Math.round((dFactor + aFactor + sFactor) * 100) * 0.01f;
	}

}
