public interface AdminDataVisitorInterface {
		
	/* Visitor pattern interface */ 
	public String visit(UserTotal userTotal, AdminControlPanel acp);
	public String visit(GroupTotal groupTotal, AdminControlPanel acp);
	public String visit(MessageTotal messageTotal, AdminControlPanel acp);
	public String visit(PosPercent posPercent, AdminControlPanel acp);
		
}

