package org.cxsbs.core;

import java.nio.ByteBuffer;

public enum MessageType {
	CONNECT(0, new Field[] { }),
	SERVINFO(1, new Field[] { }),
	WELCOME(2, new Field[] { }),
	INITCLIENT(3, new Field[] { }),
	POS(4, new Field[] { }),
	TEXT(5, new Field[] { }),
	SOUND(6, new Field[] { }),
	CDIS(7, new Field[] { }),
	SHOOT(8, new Field[] { }),
	EXPLODE(9, new Field[] { }),
	SUICIDE(10, new Field[] { }),
	DIED(11, new Field[] { }),
	DAMAGE(12, new Field[] { }),
	HITPUSH(13, new Field[] { }),
	SHOTFX(14, new Field[] { }),
	EXPLODEFX(15, new Field[] { }),
	TRYSPAWN(16, new Field[] { }),
	SPAWNSTATE(17, new Field[] { }),
	SPAWN(18, new Field[] { }),
	FORCEDEATH(19, new Field[] { }),
	GUNSELECT(20, new Field[] { }),
	TAUNT(21, new Field[] { }),
	MAPCHANGE(22, new Field[] { }),
	MAPVOTE(23, new Field[] { }),
	ITEMSPAWN(24, new Field[] { }),
	ITEMPICKUP(25, new Field[] { }),
	ITEMACC(26, new Field[] { }),
	TELEPORT(27, new Field[] { }),
	JUMPPAD(28, new Field[] { }),
	PING(29, new Field[] { }),
	PONG(30, new Field[] { }),
	CLIENTPING(31, new Field[] { }),
	TIMEUP(32, new Field[] { }),
	MAPRELOAD(33, new Field[] { }),
	FORCEINTERMISSION(34, new Field[] { }),
	SERVMSG(35, new Field[] { }),
	ITEMLIST(36, new Field[] { }),
	RESUME(37, new Field[] { }),
	EDITMODE(38, new Field[] { }),
	EDITENT(39, new Field[] { }),
	EDITF(40, new Field[] { }),
	EDITT(41, new Field[] { }),
	EDITM(42, new Field[] { }),
	FLIP(43, new Field[] { }),
	COPY(44, new Field[] { }),
	PASTE(45, new Field[] { }),
	ROTATE(46, new Field[] { }),
	REPLACE(47, new Field[] { }),
	DELCUBE(48, new Field[] { }),
	REMIP(49, new Field[] { }),
	NEWMAP(50, new Field[] { }),
	GETMAP(51, new Field[] { }),
	SENDMAP(52, new Field[] { }),
	CLIPBOARD(53, new Field[] { }),
	EDITVAR(54, new Field[] { }),
	MASTERMODE(55, new Field[] { }),
	KICK(56, new Field[] { }),
	CLEARBANS(57, new Field[] { }),
	CURRENTMASTER(58, new Field[] { }),
	SPECTATOR(59, new Field[] { }),
	SETMASTER(60, new Field[] { }),
	SETTEAM(61, new Field[] { }),
	BASES(62, new Field[] { }),
	BASEINFO(63, new Field[] { }),
	BASESCORE(64, new Field[] { }),
	REPAMMO(65, new Field[] { }),
	BASEREGEN(66, new Field[] { }),
	ANNOUNCE(67, new Field[] { }),
	LISTDEMOS(68, new Field[] { }),
	SENDDEMOLIST(69, new Field[] { }),
	GETDEMO(70, new Field[] { }),
	SENDDEMO(71, new Field[] { }),
	DEMOPLAYBACK(72, new Field[] { }),
	RECORDDEMO(73, new Field[] { }),
	STOPDEMO(74, new Field[] { }),
	CLEARDEMOS(75, new Field[] { }),
	TAKEFLAG(76, new Field[] { }),
	RETURNFLAG(77, new Field[] { }),
	RESETFLAG(78, new Field[] { }),
	INVISFLAG(79, new Field[] { }),
	TRYDROPFLAG(80, new Field[] { }),
	DROPFLAG(81, new Field[] { }),
	SCOREFLAG(82, new Field[] { }),
	INITFLAGS(83, new Field[] { }),
	SAYTEAM(84, new Field[] { }),
	CLIENT(85, new Field[] { }),
	AUTHTRY(86, new Field[] { }),
	AUTHCHAL(87, new Field[] { }),
	AUTHANS(88, new Field[] { }),
	REQAUTH(89, new Field[] { }),
	PAUSEGAME(90, new Field[] { }),
	ADDBOT(91, new Field[] { }),
	DELBOT(92, new Field[] { }),
	INITAI(93, new Field[] { }),
	FROMAI(94, new Field[] { }),
	BOTLIMIT(95, new Field[] { }),
	BOTBALANCE(96, new Field[] { }),
	MAPCRC(97, new Field[] { }),
	CHECKMAPS(98, new Field[] { }),
	SWITCHNAME(99, new Field[] { }),
	SWITCHMODEL(100, new Field[] { }),
	SWITCHTEAM(101, new Field[] { }),
	NUMSV(102, new Field[] { });
	
	private final int id;
	private final Field[] fields;
		
	private MessageType(int id, Field[] fields) {
		this.id = id;
		this.fields = fields;
	}
	
	public int getId() {
		return this.id;
	}
	
	public IMessage parseMessage(ByteBuffer buffer) {
		MessageType type = getMessageType(buffer);
		
		Message message = new Message(type);
		
		for(Field field :this.fields) {
			field.parse(buffer, message);
		}
		
		return message;
	}
	
	public static final MessageType getMessageType(ByteBuffer buffer) {
		int id = extractMessageType(buffer);
		switch(id) {
		case 0:
			return CONNECT;
		case 1:
			return SERVINFO;
		case 2:
			return WELCOME;
		case 3:
			return INITCLIENT;
		case 4:
			return POS;
		case 5:
			return TEXT;
		case 6:
			return SOUND;
		case 7:
			return CDIS;
		case 8:
			return SHOOT;
		case 9:
			return EXPLODE;
		case 10:
			return SUICIDE;
		case 11:
			return DIED;
		case 12:
			return DAMAGE;
		case 13:
			return HITPUSH;
		case 14:
			return SHOTFX;
		case 15:
			return EXPLODEFX;
		case 16:
			return TRYSPAWN;
		case 17:
			return SPAWNSTATE;
		case 18:
			return SPAWN;
		case 19:
			return FORCEDEATH;
		case 20:
			return GUNSELECT;
		case 21:
			return TAUNT;
		case 22:
			return MAPCHANGE;
		case 23:
			return MAPVOTE;
		case 24:
			return ITEMSPAWN;
		case 25:
			return ITEMPICKUP;
		case 26:
			return ITEMACC;
		case 27:
			return TELEPORT;
		case 28:
			return JUMPPAD;
		case 29:
			return PING;
		case 30:
			return PONG;
		case 31:
			return CLIENTPING;
		case 32:
			return TIMEUP;
		case 33:
			return MAPRELOAD;
		case 34:
			return FORCEINTERMISSION;
		case 35:
			return SERVMSG;
		case 36:
			return ITEMLIST;
		case 37:
			return RESUME;
		case 38:
			return EDITMODE;
		case 39:
			return EDITENT;
		case 40:
			return EDITF;
		case 41:
			return EDITT;
		case 42:
			return EDITM;
		case 43:
			return FLIP;
		case 44:
			return COPY;
		case 45:
			return PASTE;
		case 46:
			return ROTATE;
		case 47:
			return REPLACE;
		case 48:
			return DELCUBE;
		case 49:
			return REMIP;
		case 50:
			return NEWMAP;
		case 51:
			return GETMAP;
		case 52:
			return SENDMAP;
		case 53:
			return CLIPBOARD;
		case 54:
			return EDITVAR;
		case 55:
			return MASTERMODE;
		case 56:
			return KICK;
		case 57:
			return CLEARBANS;
		case 58:
			return CURRENTMASTER;
		case 59:
			return SPECTATOR;
		case 60:
			return SETMASTER;
		case 61:
			return SETTEAM;
		case 62:
			return BASES;
		case 63:
			return BASEINFO;
		case 64:
			return BASESCORE;
		case 65:
			return REPAMMO;
		case 66:
			return BASEREGEN;
		case 67:
			return ANNOUNCE;
		case 68:
			return LISTDEMOS;
		case 69:
			return SENDDEMOLIST;
		case 70:
			return GETDEMO;
		case 71:
			return SENDDEMO;
		case 72:
			return DEMOPLAYBACK;
		case 73:
			return RECORDDEMO;
		case 74:
			return STOPDEMO;
		case 75:
			return CLEARDEMOS;
		case 76:
			return TAKEFLAG;
		case 77:
			return RETURNFLAG;
		case 78:
			return RESETFLAG;
		case 79:
			return INVISFLAG;
		case 80:
			return TRYDROPFLAG;
		case 81:
			return DROPFLAG;
		case 82:
			return SCOREFLAG;
		case 83:
			return INITFLAGS;
		case 84:
			return SAYTEAM;
		case 85:
			return CLIENT;
		case 86:
			return AUTHTRY;
		case 87:
			return AUTHCHAL;
		case 88:
			return AUTHANS;
		case 89:
			return REQAUTH;
		case 90:
			return PAUSEGAME;
		case 91:
			return ADDBOT;
		case 92:
			return DELBOT;
		case 93:
			return INITAI;
		case 94:
			return FROMAI;
		case 95:
			return BOTLIMIT;
		case 96:
			return BOTBALANCE;
		case 97:
			return MAPCRC;
		case 98:
			return CHECKMAPS;
		case 99:
			return SWITCHNAME;
		case 100:
			return SWITCHMODEL;
		case 101:
			return SWITCHTEAM;
		case 102:
			return NUMSV;
		default:
			throw new IllegalArgumentException("Unknown message type");
		}
	}
	
	public static final int extractMessageType(ByteBuffer buffer) {
		return 1;
	}
}
