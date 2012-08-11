package org.cxsbs.core;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

enum MessageContext {
	STC, // Server to Client specific
	CTS, // Client to Server specific
	COM, // Common message format
	NONE;// Message with no valid context
}

public enum MessageType {
	CONNECT(MessageContext.STC, 0, new Field[] { new StringField("name"), new StringField("pwdhash"), new IntField("playermodel") }),
	SERVINFO(MessageContext.STC, 1, new Field[] { new IntField("clientnum"), new IntField("protocol_version"), new IntField("sessionid"), new IntField("haspwd"), new StringField("description") }),
	WELCOME(MessageContext.STC, 2, new Field[] { new IntField("hasmap") }),
	INITCLIENT(MessageContext.STC, 3, new Field[] { new IntField("clientnum"), new StringField("name"), new StringField("team"), new IntField("playermodel") }),
	POS(MessageContext.COM, 4, new Field[] { }), //TODO
	TEXT(MessageContext.COM, 5, new Field[] { new StringField("text") }),
	SOUND(MessageContext.COM, 6, new Field[] { new IntField("sound") }),
	CDIS(MessageContext.STC, 7, new Field[] { new IntField("clientnum") }),
	SHOOT(MessageContext.STC, 8, new Field[] { }),//TODO
	EXPLODE(MessageContext.STC, 9, new Field[] { }),//TODO
	SUICIDE(MessageContext.CTS, 10, new Field[] { }),
	DIED(MessageContext.STC, 11, new Field[] { new IntField("clientnum"), new IntField("killer"), new IntField("frags") }),
	DAMAGE(MessageContext.STC, 12, new Field[] { new IntField("clientnum"), new IntField("aggressor"), new IntField("damage"), new IntField("armour"), new IntField("health") }),
	HITPUSH(MessageContext.STC, 13, new Field[] { new IntField("clientnum"), new IntField("gun"), new IntField("damage"), new VectorField("direction") }),
	SHOTFX(MessageContext.STC, 14, new Field[] { }),
	EXPLODEFX(MessageContext.STC, 15, new Field[] { }),
	TRYSPAWN(MessageContext.CTS, 16, new Field[] { }),
	SPAWNSTATE(MessageContext.STC, 17, new Field[] { }),
	STC_SPAWN(MessageContext.STC, 18, new Field[] { }),
	CTS_SPAWN(MessageContext.CTS, 18, new Field[] { }),
	FORCEDEATH(MessageContext.STC, 19, new Field[] { }),
	GUNSELECT(MessageContext.COM, 20, new Field[] { }),
	TAUNT(MessageContext.COM, 21, new Field[] { }),
	MAPCHANGE(MessageContext.COM, 22, new Field[] { }),
	MAPVOTE(MessageContext.CTS, 23, new Field[] { }),
	ITEMSPAWN(MessageContext.STC, 24, new Field[] { }),
	ITEMPICKUP(MessageContext.CTS, 25, new Field[] { }),
	ITEMACC(MessageContext.STC, 26, new Field[] { }),
	TELEPORT(MessageContext.COM, 27, new Field[] { }),
	JUMPPAD(MessageContext.COM, 28, new Field[] { }),
	PING(MessageContext.CTS, 29, new Field[] { }),
	PONG(MessageContext.STC, 30, new Field[] { }),
	CLIENTPING(MessageContext.COM, 31, new Field[] { }),
	TIMEUP(MessageContext.STC, 32, new Field[] { }),
	MAPRELOAD(MessageContext.STC, 33, new Field[] { }),
	FORCEINTERMISSION(MessageContext.CTS, 34, new Field[] { }),
	SERVMSG(MessageContext.STC, 35, new Field[] { }),
	ITEMLIST(MessageContext.COM, 36, new Field[] { }),
	RESUME(MessageContext.STC, 37, new Field[] { }),
	EDITMODE(MessageContext.COM, 38, new Field[] { }),
	EDITENT(MessageContext.COM, 39, new Field[] { }),
	EDITF(MessageContext.COM, 40, new Field[] { }),
	EDITT(MessageContext.COM, 41, new Field[] { }),
	EDITM(MessageContext.COM, 42, new Field[] { }),
	FLIP(MessageContext.COM, 43, new Field[] { }),
	COPY(MessageContext.COM, 44, new Field[] { }),
	PASTE(MessageContext.COM, 45, new Field[] { }),
	ROTATE(MessageContext.COM, 46, new Field[] { }),
	REPLACE(MessageContext.COM, 47, new Field[] { }),
	DELCUBE(MessageContext.COM, 48, new Field[] { }),
	REMIP(MessageContext.COM, 49, new Field[] { }),
	NEWMAP(MessageContext.COM, 50, new Field[] { }),
	GETMAP(MessageContext.CTS, 51, new Field[] { }),
	SENDMAP(MessageContext.STC, 52, new Field[] { }),
	CLIPBOARD(MessageContext.COM, 53, new Field[] { }),
	EDITVAR(MessageContext.COM, 54, new Field[] { }),
	MASTERMODE(MessageContext.COM, 55, new Field[] { }),
	KICK(MessageContext.CTS, 56, new Field[] { }),
	CLEARBANS(MessageContext.CTS, 57, new Field[] { }),
	CURRENTMASTER(MessageContext.STC, 58, new Field[] { }),
	SPECTATOR(MessageContext.COM, 59, new Field[] { }),
	SETMASTER(MessageContext.CTS, 60, new Field[] { }),
	SETTEAM(MessageContext.COM, 61, new Field[] { }),
	CTS_BASES(MessageContext.CTS, 62, new Field[] { }),
	STC_BASES(MessageContext.STC, 62, new Field[] { }),
	BASEINFO(MessageContext.STC, 63, new Field[] { }),
	BASESCORE(MessageContext.STC, 64, new Field[] { }),
	REPAMMO(MessageContext.STC, 65, new Field[] { }),
	BASEREGEN(MessageContext.STC, 66, new Field[] { }),
	ANNOUNCE(MessageContext.STC, 67, new Field[] { }),
	LISTDEMOS(MessageContext.CTS, 68, new Field[] { }),
	SENDDEMOLIST(MessageContext.STC, 69, new Field[] { }),
	GETDEMO(MessageContext.CTS, 70, new Field[] { }),
	SENDDEMO(MessageContext.STC, 71, new Field[] { }),
	DEMOPLAYBACK(MessageContext.STC, 72, new Field[] { }),
	RECORDDEMO(MessageContext.CTS, 73, new Field[] { }),
	STOPDEMO(MessageContext.CTS, 74, new Field[] { }),
	CLEARDEMOS(MessageContext.CTS, 75, new Field[] { }),
	CTS_TAKEFLAG(MessageContext.CTS, 76, new Field[] { }),
	STC_TAKEFLAG(MessageContext.STC, 76, new Field[] { }),
	RETURNFLAG(MessageContext.STC, 77, new Field[] { }),
	RESETFLAG(MessageContext.STC, 78, new Field[] { }),
	INVISFLAG(MessageContext.STC, 79, new Field[] { }),
	TRYDROPFLAG(MessageContext.CTS, 80, new Field[] { }),
	DROPFLAG(MessageContext.STC, 81, new Field[] { }),
	SCOREFLAG(MessageContext.STC, 82, new Field[] { }),
	CTS_INITFLAGS(MessageContext.CTS, 83, new Field[] { }),
	STC_INITFLAGS(MessageContext.STC, 83, new Field[] { }),
	SAYTEAM(MessageContext.COM, 84, new Field[] { }),
	CLIENT(MessageContext.STC, 85, new Field[] { }),
	AUTHTRY(MessageContext.CTS, 86, new Field[] { }),
	AUTHCHAL(MessageContext.STC, 87, new Field[] { }),
	AUTHANS(MessageContext.CTS, 88, new Field[] { }),
	REQAUTH(MessageContext.STC, 89, new Field[] { }),
	PAUSEGAME(MessageContext.COM, 90, new Field[] { }),
	ADDBOT(MessageContext.CTS, 91, new Field[] { }),
	DELBOT(MessageContext.CTS, 92, new Field[] { }),
	INITAI(MessageContext.STC, 93, new Field[] { }),
	FROMAI(MessageContext.CTS, 94, new Field[] { }),
	BOTLIMIT(MessageContext.CTS, 95, new Field[] { }),
	BOTBALANCE(MessageContext.CTS, 96, new Field[] { }),
	MAPCRC(MessageContext.CTS, 97, new Field[] { }),
	CHECKMAPS(MessageContext.CTS, 98, new Field[] { }),
	SWITCHNAME(MessageContext.COM, 99, new Field[] { }),
	SWITCHMODEL(MessageContext.COM, 100, new Field[] { }),
	SWITCHTEAM(MessageContext.CTS, 101, new Field[] { }),
	NUMSV(MessageContext.NONE, 102, new Field[] { });
	
	private final MessageContext context;
	private final int id;
	private final Field[] fields;
	
	public static Map<Integer, MessageType> stcMessages = new HashMap<Integer, MessageType>();
	public static Map<Integer, MessageType> ctsMessages = new HashMap<Integer, MessageType>();
	
	private MessageType(MessageContext context, int id, Field[] fields) {
		this.context = context;
		this.id = id;
		this.fields = fields;
	}
	
	public int getId() {
		return this.id;
	}
	
	public IMessage parseMessage(ByteBuffer buffer, MessageContext context) {
		MessageType type = getMessageType(buffer, context);
		
		Message message = new Message(type);
		
		for(Field field :this.fields) {
			field.parse(buffer, message);
		}
		
		return message;
	}
	
	public static final MessageType getMessageType(ByteBuffer buffer, MessageContext context) {
		int id = extractMessageType(buffer);
		
		if (context == MessageContext.CTS) {
			if (ctsMessages.containsKey(id)) {
				return ctsMessages.get(id);
			} else {
				throw new IllegalArgumentException("Unknown message type for this context");
			}
		} else if (context == MessageContext.STC) {
			if (stcMessages.containsKey(id)) {
				return stcMessages.get(id);
			} else {
				throw new IllegalArgumentException("Unknown message type for this context");
			}
		} else {
			throw new IllegalArgumentException("Cannot get message types for this context.");
		}
	}
	
	public static final int extractMessageType(ByteBuffer buffer) {
		return CubeByteBuffer.getint(buffer, false);
	}
	
    static {
        for (MessageType messageType : MessageType.values())
    		if(messageType.context == MessageContext.CTS) {
    			ctsMessages.put(messageType.id, messageType);
    		} else if (messageType.context == MessageContext.STC) {
    			stcMessages.put(messageType.id, messageType);
    		} else if (messageType.context == MessageContext.COM) {
    			ctsMessages.put(messageType.id, messageType);
    			stcMessages.put(messageType.id, messageType);
    		}
      }
}
