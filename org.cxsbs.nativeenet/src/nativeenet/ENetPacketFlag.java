package nativeenet;

public enum ENetPacketFlag {
	NONE(0),
	/**
	 * packet must be received by the target peer and resend attempts should be
	 * made until the packet is delivered
	 */
	RELIABLE(1 << 0),
	/**
	 * packet will not be sequenced with other packets not supported for
	 * reliable packets
	 */
	UNSEQUENCED(1 << 1),
	/** packet will not allocate data, and user must supply it instead */
	NO_ALLOCATE(1 << 2),
	/**
	 * packet will be fragmented using unreliable (instead of reliable) sends if
	 * it exceeds the MTU
	 */
	UNRELIABLE_FRAGMENT(1 << 3);

	public final int value;

	ENetPacketFlag(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
	
	public static int or(ENetPacketFlag...flags) {
		int result = 0;
		for (ENetPacketFlag flag : flags) {
			result = result | flag.value();
		}
		return result;
	}
	
};
