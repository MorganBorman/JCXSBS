package org.cxsbs.core;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public enum MessageType {
	CONNECT(MessageContext.CTS, 0, new Field[] { new StringField("name"), new StringField("pwdhash"), new IntField("playermodel") }),
	SERVINFO(MessageContext.STC, 1, new Field[] { new IntField("clientnum"), new IntField("protocol_version"), new IntField("sessionid"), new IntField("haspwd"), new StringField("description") }),
	WELCOME(MessageContext.STC, 2, new Field[] { new IntField("hasmap") }),
	INITCLIENT(MessageContext.STC, 3, new Field[] { new IntField("clientnum"), new StringField("name"), new StringField("team"), new IntField("playermodel") }),
	POS(MessageContext.COM, 4, new Field[] { new ClientPositionField("position") }),
	TEXT(MessageContext.COM, 5, new Field[] { new StringField("text") }),
	SOUND(MessageContext.COM, 6, new Field[] { new IntField("sound") }),
	CDIS(MessageContext.STC, 7, new Field[] { new IntField("clientnum") }),
	SHOOT(MessageContext.CTS, 8, new Field[] { new IntField("shotid"), new IntField("gun"), new VectorField("from"), new VectorField("to"), new IteratedFields("hits", new Field[] { new HitField("hit") }) }),
	EXPLODE(MessageContext.CTS, 9, new Field[] { new IntField("cmillis"), new IntField("gun"), new IntField("shotid"), new IteratedFields("hits", new Field[] { new HitField("hit") }) }),
	SUICIDE(MessageContext.CTS, 10, new Field[] { }),
	DIED(MessageContext.STC, 11, new Field[] { new IntField("clientnum"), new IntField("killer"), new IntField("frags") }),
	DAMAGE(MessageContext.STC, 12, new Field[] { new IntField("clientnum"), new IntField("aggressor"), new IntField("damage"), new IntField("armour"), new IntField("health") }),
	HITPUSH(MessageContext.STC, 13, new Field[] { new IntField("clientnum"), new IntField("gun"), new IntField("damage"), new VectorField("direction") }),
	SHOTFX(MessageContext.STC, 14, new Field[] { new IntField("clientnum"), new IntField("gun"), new IntField("shotid"), new VectorField("from"), new VectorField("to")}),
	EXPLODEFX(MessageContext.STC, 15, new Field[] { new IntField("clientnum"), new IntField("gun"), new IntField("shotid") }),
	TRYSPAWN(MessageContext.CTS, 16, new Field[] { }),
	SPAWNSTATE(MessageContext.STC, 17, new Field[] { new IntField("clientnum"), new ClientStateField("state"), new AmmoListField("ammo") }),
	STC_SPAWN(MessageContext.STC, 18, new Field[] { new ClientStateField("state"), new IntField("guncount"), new AmmoListField("ammo") }),
	CTS_SPAWN(MessageContext.CTS, 18, new Field[] { new IntField("lifesequence"), new IntField("gunselect") }),
	FORCEDEATH(MessageContext.STC, 19, new Field[] { new IntField("clientnum") }),
	GUNSELECT(MessageContext.COM, 20, new Field[] { new IntField("gun") }),
	TAUNT(MessageContext.COM, 21, new Field[] { }),
	CTS_MAPCHANGE(MessageContext.CTS, 22, new Field[] { new StringField("map"), new IntField("mode") }),
	STC_MAPCHANGE(MessageContext.STC, 22, new Field[] { new StringField("map"), new IntField("mode"), new IntField("senditems") }),
	MAPVOTE(MessageContext.CTS, 23, new Field[] { new StringField("map"), new IntField("mode") }),
	ITEMSPAWN(MessageContext.STC, 24, new Field[] { new IntField("itemid") }),
	ITEMPICKUP(MessageContext.CTS, 25, new Field[] { new IntField("itemid") }),
	ITEMACC(MessageContext.STC, 26, new Field[] { new IntField("itemid"), new IntField("clientnum") }),
	TELEPORT(MessageContext.COM, 27, new Field[] { new IntField("clientnum"), new IntField("teleport"), new IntField("teledest") }),
	JUMPPAD(MessageContext.COM, 28, new Field[] { new IntField("clientnum"), new IntField("jumppad") }),
	PING(MessageContext.CTS, 29, new Field[] { new IntField("clientmillis") }),
	PONG(MessageContext.STC, 30, new Field[] { new IntField("clientmillis") }),
	CLIENTPING(MessageContext.COM, 31, new Field[] { new IntField("ping") }),
	TIMEUP(MessageContext.STC, 32, new Field[] { new IntField("timeleft") }),
	MAPRELOAD(MessageContext.STC, 33, new Field[] { }),
	FORCEINTERMISSION(MessageContext.CTS, 34, new Field[] { }),
	SERVMSG(MessageContext.STC, 35, new Field[] { new StringField("text") }),
	ITEMLIST(MessageContext.COM, 36, new Field[] { new TerminatedFields("items", new Field[] { new IntField("itemid"), new IntField("itemtype") }) }),
	RESUME(MessageContext.STC, 37, new Field[] { new TerminatedFields("items", new Field[] { new IntField("clientnum"), new IntField("cstate"), new IntField("frags"), new IntField("flags"), new IntField("quadexpiry"), new ClientStateField("state"), new AmmoListField("ammo") }) }),
	EDITMODE(MessageContext.COM, 38, new Field[] { new IntField("editting") }),
	EDITENT(MessageContext.COM, 39, new Field[] { new IntField("entid"), new VectorField("position"), new IntField("type"), new IntField("attr1"), new IntField("attr2"), new IntField("attr3"), new IntField("attr4"), new IntField("attr5") }),
	EDITF(MessageContext.COM, 40, new Field[] { }),//TODO editing messages
	EDITT(MessageContext.COM, 41, new Field[] { }),//TODO editing messages
	EDITM(MessageContext.COM, 42, new Field[] { }),//TODO editing messages
	FLIP(MessageContext.COM, 43, new Field[] { }),//TODO editing messages
	COPY(MessageContext.COM, 44, new Field[] { }),//TODO editing messages
	PASTE(MessageContext.COM, 45, new Field[] { }),//TODO editing messages
	ROTATE(MessageContext.COM, 46, new Field[] { }),//TODO editing messages
	REPLACE(MessageContext.COM, 47, new Field[] { }),//TODO editing messages
	DELCUBE(MessageContext.COM, 48, new Field[] { }),//TODO editing messages
	REMIP(MessageContext.COM, 49, new Field[] { }),//TODO editing messages
	NEWMAP(MessageContext.COM, 50, new Field[] { }),//TODO editing messages
	GETMAP(MessageContext.CTS, 51, new Field[] { }),//TODO editing messages
	SENDMAP(MessageContext.STC, 52, new Field[] { }),//TODO editing messages
	CLIPBOARD(MessageContext.COM, 53, new Field[] { }),//TODO editing messages
	EDITVAR(MessageContext.COM, 54, new Field[] { }),//TODO editing messages
	MASTERMODE(MessageContext.COM, 55, new Field[] { new IntField("mastermode") }),
	KICK(MessageContext.CTS, 56, new Field[] { new IntField("clientnum") }),
	CLEARBANS(MessageContext.CTS, 57, new Field[] { }),
	CURRENTMASTER(MessageContext.STC, 58, new Field[] { new IntField("clientnum"), new IntField("privilege"), new IntField("mastermode") }),
	SPECTATOR(MessageContext.COM, 59, new Field[] { new IntField("clientnum"), new IntField("spectated") }),
	SETMASTER(MessageContext.CTS, 60, new Field[] { new IntField("tryclaim"), new StringField("pwdhash") }),
	CTS_SETTEAM(MessageContext.CTS, 61, new Field[] { new IntField("clientnum"), new StringField("team") }),
	STC_SETTEAM(MessageContext.STC, 61, new Field[] { new IntField("clientnum"), new StringField("team"), new IntField("reason") }),
	CTS_BASES(MessageContext.CTS, 62, new Field[] { }),//TODO capture modes
	STC_BASES(MessageContext.STC, 62, new Field[] { }),//TODO capture modes
	BASEINFO(MessageContext.STC, 63, new Field[] { }),//TODO capture modes
	BASESCORE(MessageContext.STC, 64, new Field[] { }),//TODO capture modes
	REPAMMO(MessageContext.STC, 65, new Field[] { }),//TODO capture modes
	BASEREGEN(MessageContext.STC, 66, new Field[] { }),//TODO capture modes
	ANNOUNCE(MessageContext.STC, 67, new Field[] { new IntField("itemtype") }),
	LISTDEMOS(MessageContext.CTS, 68, new Field[] { }),
	SENDDEMOLIST(MessageContext.STC, 69, new Field[] {  }),//TODO iteration for demo list retrieval
	GETDEMO(MessageContext.CTS, 70, new Field[] { new IntField("demonum") }),
	SENDDEMO(MessageContext.STC, 71, new Field[] { }),//TODO incoming files
	DEMOPLAYBACK(MessageContext.STC, 72, new Field[] { new IntField("value"), new IntField("clientnum") }),
	RECORDDEMO(MessageContext.CTS, 73, new Field[] { new IntField("record") }),
	STOPDEMO(MessageContext.CTS, 74, new Field[] { }),
	CLEARDEMOS(MessageContext.CTS, 75, new Field[] { new IntField("demonum") }),
	CTS_TAKEFLAG(MessageContext.CTS, 76, new Field[] { }),//TODO flag modes
	STC_TAKEFLAG(MessageContext.STC, 76, new Field[] { }),//TODO flag modes
	RETURNFLAG(MessageContext.STC, 77, new Field[] { }),//TODO flag modes
	RESETFLAG(MessageContext.STC, 78, new Field[] { }),//TODO flag modes
	INVISFLAG(MessageContext.STC, 79, new Field[] { }),//TODO flag modes
	TRYDROPFLAG(MessageContext.CTS, 80, new Field[] { }),//TODO flag modes
	DROPFLAG(MessageContext.STC, 81, new Field[] { }),//TODO flag modes
	SCOREFLAG(MessageContext.STC, 82, new Field[] { }),//TODO flag modes
	CTS_INITFLAGS(MessageContext.CTS, 83, new Field[] { }),//TODO flag modes
	STC_INITFLAGS(MessageContext.STC, 83, new Field[] { }),//TODO flag modes
	SAYTEAM(MessageContext.COM, 84, new Field[] { new IntField("text") }),
	CLIENT(MessageContext.STC, 85, new Field[] { }),//TODO
	AUTHTRY(MessageContext.CTS, 86, new Field[] { new StringField("domain"), new StringField("authname") }),
	AUTHCHAL(MessageContext.STC, 87, new Field[] { new StringField("domain"), new UIntField("authid"), new StringField("challenge") }),
	AUTHANS(MessageContext.CTS, 88, new Field[] { new StringField("domain"), new UIntField("authid"), new StringField("answerhash") }),
	REQAUTH(MessageContext.STC, 89, new Field[] { new StringField("domain") }),
	PAUSEGAME(MessageContext.COM, 90, new Field[] { new IntField("value") }),
	ADDBOT(MessageContext.CTS, 91, new Field[] { }),//TODO bots
	DELBOT(MessageContext.CTS, 92, new Field[] { }),//TODO bots
	INITAI(MessageContext.STC, 93, new Field[] { }),//TODO bots
	FROMAI(MessageContext.CTS, 94, new Field[] { }),//TODO bots
	BOTLIMIT(MessageContext.CTS, 95, new Field[] { }),//TODO bots
	BOTBALANCE(MessageContext.CTS, 96, new Field[] { }),//TODO bots
	MAPCRC(MessageContext.CTS, 97, new Field[] { new StringField("map"), new IntField("crc") }),
	CHECKMAPS(MessageContext.CTS, 98, new Field[] { }),
	SWITCHNAME(MessageContext.COM, 99, new Field[] { new StringField("name") }),
	SWITCHMODEL(MessageContext.COM, 100, new Field[] { new IntField("playermodel") }),
	SWITCHTEAM(MessageContext.CTS, 101, new Field[] { new StringField("team") }),
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
	
	public static IMessage parseMessage(ByteBuffer buffer, MessageContext context) {
		MessageType type = getMessageType(buffer, context);
		
		Message message = new Message(type);
		
		for(Field field : type.fields) {
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
				throw new IllegalArgumentException("Unknown message type (" + id + ") for this context");
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
