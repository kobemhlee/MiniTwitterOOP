package MiniTwitterA2;

public class AdminVisitor implements AdminDataVisitorInterface {

	@Override
	public String visit(UserTotal userTotal, AdminControlPanel acp) {
		return acp.getTotalUsers();
	}

	@Override
	public String visit(GroupTotal groupTotal, AdminControlPanel acp) {
		return acp.getTotalGroups();
	}

	@Override
	public String visit(MessageTotal messageTotal, AdminControlPanel acp) {
		return acp.getTotalTweets();
	}

	@Override
	public String visit(PosPercent posPercent, AdminControlPanel acp) {
		return acp.getTotalPositiveTweets();
	}
}
