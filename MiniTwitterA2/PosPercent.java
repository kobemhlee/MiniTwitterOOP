package MiniTwitterA2;

public class PosPercent implements AdminDataInterface {
	
	@Override
	public String accept(AdminDataVisitorInterface acpVisitor, AdminControlPanel acp) {
		return acpVisitor.visit(this, acp);
	}

}
