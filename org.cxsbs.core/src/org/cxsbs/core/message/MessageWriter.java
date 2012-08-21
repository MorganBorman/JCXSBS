package org.cxsbs.core.message;

import java.nio.ByteBuffer;

import org.cxsbs.core.Client;
import org.cxsbs.core.ClientAmmo;
import org.cxsbs.core.ClientState;
import org.cxsbs.core.CubeByteBuffer;
import org.cxsbs.core.IClient;
import org.cxsbs.core.Vector;
import org.cxsbs.core.WeaponType;

public class MessageWriter {
	public static void PutConnect(ByteBuffer buffer, String name, String pwdhash, int playermodel) {
		CubeByteBuffer.putint(buffer, MessageType.CONNECT.getId());
		CubeByteBuffer.putstring(buffer, name);
		CubeByteBuffer.putstring(buffer, pwdhash);
		CubeByteBuffer.putint(buffer, playermodel);
	}
	
	public static void PutServerInfo(ByteBuffer buffer, IClient client, boolean haspwd, String serverDescription) {
		CubeByteBuffer.putint(buffer, MessageType.SERVINFO.getId());
		CubeByteBuffer.putint(buffer, client.getCn()); 					// id
		CubeByteBuffer.putint(buffer, 258); 							//PROTOCOL_VERSION = 258
		CubeByteBuffer.putint(buffer, client.getCn()); 					// cn
		CubeByteBuffer.putbool(buffer, haspwd);
		CubeByteBuffer.putstring(buffer, serverDescription);
	}
	
	public static void PutWelcome(ByteBuffer buffer, boolean hasmap) {
		CubeByteBuffer.putint(buffer, MessageType.WELCOME.getId());
		CubeByteBuffer.putbool(buffer, hasmap);
	}
	
	public static void PutInitClient(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.INITCLIENT.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putstring(buffer, "");//name);
		CubeByteBuffer.putstring(buffer, "");//team);
		CubeByteBuffer.putint(buffer, 0);//playermodel);
	}
	
	public static void PutPos(ByteBuffer buffer, IClient client) throws NotImplementedException {
		throw new NotImplementedException(); //TODO
	}
	
	public static void PutText(ByteBuffer buffer, String text) {
		CubeByteBuffer.putint(buffer, MessageType.TEXT.getId());
		CubeByteBuffer.putstring(buffer, text);
	}
	
	public static void PutSound(ByteBuffer buffer, int sound) {
		CubeByteBuffer.putint(buffer, MessageType.SOUND.getId());
		CubeByteBuffer.putint(buffer, sound);
	}
	
	public static void PutCdis(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.CDIS.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
	}
	
	public static void PutShoot(ByteBuffer buffer) throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	public static void PutExplode(ByteBuffer buffer) throws NotImplementedException {
		throw new NotImplementedException();
	}
	
	public static void PutSuicide(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.SUICIDE.getId());
	}
	
	public static void PutDied(ByteBuffer buffer, Client client, IClient killer) {
		CubeByteBuffer.putint(buffer, MessageType.DIED.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, killer.getCn());
		CubeByteBuffer.putint(buffer, 0);//killer.getFrags());
	}
	
	public static void PutDamage(ByteBuffer buffer, IClient client, Client aggressor, int damage) {
		CubeByteBuffer.putint(buffer, MessageType.DAMAGE.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, aggressor.getCn());
		CubeByteBuffer.putint(buffer, damage);//killer.getFrags());
		CubeByteBuffer.putint(buffer, 100);//killer.getArmour());
		CubeByteBuffer.putint(buffer, 100);//killer.getHealth());
	}
	
	public static void PutHitpush(ByteBuffer buffer, IClient client, int gun, int damage, Vector direction) {
		CubeByteBuffer.putint(buffer, MessageType.HITPUSH.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, gun);
		CubeByteBuffer.putint(buffer, damage);
		CubeByteBuffer.putint(buffer, direction.getX());
		CubeByteBuffer.putint(buffer, direction.getY());
		CubeByteBuffer.putint(buffer, direction.getZ());
	}
	
	public static void PutShotFx(ByteBuffer buffer, IClient client, int gun, int shotid, Vector from, Vector to) {
		CubeByteBuffer.putint(buffer, MessageType.SHOTFX.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, gun);
		CubeByteBuffer.putint(buffer, shotid);
		CubeByteBuffer.putint(buffer, from.getX());
		CubeByteBuffer.putint(buffer, from.getY());
		CubeByteBuffer.putint(buffer, from.getZ());
		CubeByteBuffer.putint(buffer, to.getX());
		CubeByteBuffer.putint(buffer, to.getY());
		CubeByteBuffer.putint(buffer, to.getZ());
	}
	
	public static void PutExplodeFx(ByteBuffer buffer, IClient client, int gun, int shotid) {
		CubeByteBuffer.putint(buffer, MessageType.EXPLODEFX.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, gun);
		CubeByteBuffer.putint(buffer, shotid);
	}
	
	public static void PutTrySpawn(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.TRYSPAWN.getId());
	}
	
	private static void PutClientState(ByteBuffer buffer, ClientState clientState) {
		CubeByteBuffer.putint(buffer, clientState.getLifesequence());
		CubeByteBuffer.putint(buffer, clientState.getHealth());
		CubeByteBuffer.putint(buffer, clientState.getMaxhealth());
		CubeByteBuffer.putint(buffer, clientState.getArmour());
		CubeByteBuffer.putint(buffer, clientState.getArmourType());
		CubeByteBuffer.putint(buffer, clientState.getGunselect());
	}
	
	private static void PutClientAmmo(ByteBuffer buffer, ClientAmmo clientAmmo) {
		for(int i = WeaponType.SG.ordinal(); i <= WeaponType.PISTOL.ordinal(); i++) {
			CubeByteBuffer.putint(buffer, clientAmmo.getEntry(i));
		}
	}
	
	public static void PutSpawnState(ByteBuffer buffer, IClient client, int gun, int shotid) {
		CubeByteBuffer.putint(buffer, MessageType.SPAWNSTATE.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		PutClientState(buffer, client.getState());
		PutClientAmmo(buffer, client.getState().getAmmo());
	}
	
	public static void PutSTCSpawn(ByteBuffer buffer, ClientState clientState) {
		CubeByteBuffer.putint(buffer, MessageType.STC_SPAWN.getId());
		PutClientState(buffer, clientState);
		CubeByteBuffer.putint(buffer, WeaponType.PISTOL.ordinal()-WeaponType.SG.ordinal()+1);
		PutClientAmmo(buffer, clientState.getAmmo());
	}
	
	public static void PutCTSSpawn(ByteBuffer buffer, ClientState clientState) {
		CubeByteBuffer.putint(buffer, MessageType.CTS_SPAWN.getId());
		CubeByteBuffer.putint(buffer, clientState.getLifesequence());
		CubeByteBuffer.putint(buffer, clientState.getGunselect());
	}
	
	public static void PutForceDeath(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.FORCEDEATH.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
	}
	
	public static void PutGunselect(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.GUNSELECT.getId());
		CubeByteBuffer.putint(buffer, client.getState().getGunselect());
	}
	
	public static void PutTaunt(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.TAUNT.getId());
	}
	
	public static void PutCTSMapChange(ByteBuffer buffer, String mapName, int modeNum) {
		CubeByteBuffer.putint(buffer, MessageType.CTS_MAPCHANGE.getId());
		CubeByteBuffer.putstring(buffer, mapName);
		CubeByteBuffer.putint(buffer, modeNum);
	}
	
	public static void PutSTCMapChange(ByteBuffer buffer, String mapName, int modeNum, boolean sendItems) {
		CubeByteBuffer.putint(buffer, MessageType.STC_MAPCHANGE.getId());
		CubeByteBuffer.putstring(buffer, mapName);
		CubeByteBuffer.putint(buffer, modeNum);
		CubeByteBuffer.putbool(buffer, sendItems);
	}
	
	public static void PutMapVote(ByteBuffer buffer, String mapName, int modeNum) {
		CubeByteBuffer.putint(buffer, MessageType.MAPVOTE.getId());
		CubeByteBuffer.putstring(buffer, mapName);
		CubeByteBuffer.putint(buffer, modeNum);
	}
	
	public static void PutItemSpawn(ByteBuffer buffer, int itemid) {
		CubeByteBuffer.putint(buffer, MessageType.ITEMSPAWN.getId());
		CubeByteBuffer.putint(buffer, itemid);
	}
	
	public static void PutItemPickup(ByteBuffer buffer, int itemid) {
		CubeByteBuffer.putint(buffer, MessageType.ITEMPICKUP.getId());
		CubeByteBuffer.putint(buffer, itemid);
	}
	
	public static void PutItemAcknowlege(ByteBuffer buffer, IClient client, int itemid) {
		CubeByteBuffer.putint(buffer, MessageType.ITEMACC.getId());
		CubeByteBuffer.putint(buffer, itemid);
		CubeByteBuffer.putint(buffer, client.getCn());
	}
	
	public static void PutTeleport(ByteBuffer buffer, IClient client, int teleport, int teledest) {
		CubeByteBuffer.putint(buffer, MessageType.TELEPORT.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, teleport);
		CubeByteBuffer.putint(buffer, teledest);
	}
	
	public static void PutJumppad(ByteBuffer buffer, IClient client, int jumppad) {
		CubeByteBuffer.putint(buffer, MessageType.JUMPPAD.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, jumppad);
	}
	
	public static void PutPing(ByteBuffer buffer, int clientmillis) {
		CubeByteBuffer.putint(buffer, MessageType.PING.getId());
		CubeByteBuffer.putint(buffer, clientmillis);
	}
	
	public static void PutPong(ByteBuffer buffer, int clientmillis) {
		CubeByteBuffer.putint(buffer, MessageType.PONG.getId());
		CubeByteBuffer.putint(buffer, clientmillis);
	}
	
	public static void PutClientPing(ByteBuffer buffer, int ping) {
		CubeByteBuffer.putint(buffer, MessageType.CLIENTPING.getId());
		CubeByteBuffer.putint(buffer, ping);
	}
	
	public static void PutTimeup(ByteBuffer buffer, int timeleft) {
		CubeByteBuffer.putint(buffer, MessageType.TIMEUP.getId());
		CubeByteBuffer.putint(buffer, timeleft);
	}
	
	public static void PutMapReload(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.MAPRELOAD.getId());
	}
	
	public static void PutForceIntermission(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.FORCEINTERMISSION.getId());
	}
	
	public static void PutServerMessage(ByteBuffer buffer, String text) {
		CubeByteBuffer.putint(buffer, MessageType.SERVMSG.getId());
		CubeByteBuffer.putstring(buffer, text);
	}
	
	/*
	ITEMLIST(MessageContext.COM, 36, new Field[] { new TerminatedFields("items", new Field[] { new IntField("itemid"), new IntField("itemtype") }) }),
	RESUME(MessageContext.STC, 37, new Field[] { new TerminatedFields("clients", new Field[] { new IntField("clientnum"), new IntField("cstate"), new IntField("frags"), new IntField("flags"), new IntField("quadexpiry"), new ClientStateField("state"), new AmmoListField("ammo") }) }),
	*/
	
	public static void PutEditMode(ByteBuffer buffer, boolean editing) {
		CubeByteBuffer.putint(buffer, MessageType.EDITMODE.getId());
		CubeByteBuffer.putbool(buffer, editing);
	}
	
	public static void PutEditEntity(ByteBuffer buffer, Vector position, int type, int attr1, int attr2, int attr3, int attr4, int attr5) {
		CubeByteBuffer.putint(buffer, MessageType.EDITENT.getId());
		CubeByteBuffer.putint(buffer, position.getX());
		CubeByteBuffer.putint(buffer, position.getY());
		CubeByteBuffer.putint(buffer, position.getZ());
		CubeByteBuffer.putint(buffer, type);
		CubeByteBuffer.putint(buffer, attr1);
		CubeByteBuffer.putint(buffer, attr2);
		CubeByteBuffer.putint(buffer, attr3);
		CubeByteBuffer.putint(buffer, attr4);
		CubeByteBuffer.putint(buffer, attr5);
	}
	
	/*
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
	*/
	
	public static void PutMastermode(ByteBuffer buffer, int mastermode) {
		CubeByteBuffer.putint(buffer, MessageType.MASTERMODE.getId());
		CubeByteBuffer.putint(buffer, mastermode);
	}
	
	public static void PutKick(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.MASTERMODE.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
	}
	
	public static void PutClearBans(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.CLEARBANS.getId());
	}
	
	public static void PutCurrentMaster(ByteBuffer buffer, IClient client, int privilege, int mastermode) {
		CubeByteBuffer.putint(buffer, MessageType.CURRENTMASTER.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putint(buffer, privilege);
		CubeByteBuffer.putint(buffer, mastermode);
	}
	
	public static void PutSpectator(ByteBuffer buffer, IClient client, boolean spectated) {
		CubeByteBuffer.putint(buffer, MessageType.SPECTATOR.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putbool(buffer, spectated);
	}
	
	public static void PutSetMaster(ByteBuffer buffer, IClient client, boolean tryclaim, String pwdhash) {
		CubeByteBuffer.putint(buffer, MessageType.SETMASTER.getId());
		CubeByteBuffer.putbool(buffer, tryclaim);
		CubeByteBuffer.putstring(buffer, pwdhash);
	}
	
	public static void PutSetCTSTeam(ByteBuffer buffer, IClient client) {
		CubeByteBuffer.putint(buffer, MessageType.CTS_SETTEAM.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putstring(buffer, "");//TODO: team
	}
	
	public static void PutSetSTCTeam(ByteBuffer buffer, IClient client, boolean forced) {
		CubeByteBuffer.putint(buffer, MessageType.STC_SETTEAM.getId());
		CubeByteBuffer.putint(buffer, client.getCn());
		CubeByteBuffer.putstring(buffer, "");//TODO: team
		CubeByteBuffer.putbool(buffer, forced);
	}
	
	/*
	CTS_BASES(MessageContext.CTS, 62, new Field[] { }),//TODO capture modes
	STC_BASES(MessageContext.STC, 62, new Field[] { }),//TODO capture modes
	BASEINFO(MessageContext.STC, 63, new Field[] { }),//TODO capture modes
	BASESCORE(MessageContext.STC, 64, new Field[] { }),//TODO capture modes
	REPAMMO(MessageContext.STC, 65, new Field[] { }),//TODO capture modes
	BASEREGEN(MessageContext.STC, 66, new Field[] { }),//TODO capture modes
	*/
	
	public static void PutAnnounce(ByteBuffer buffer, int itemType) {
		CubeByteBuffer.putint(buffer, MessageType.ANNOUNCE.getId());
		CubeByteBuffer.putint(buffer, itemType);
	}
	
	public static void PutListDemos(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.LISTDEMOS.getId());
	}
	
	/*
	SENDDEMOLIST(MessageContext.STC, 69, new Field[] {  }),//TODO iteration for demo list retrieval
	*/
	
	public static void PutGetDemo(ByteBuffer buffer, int demoNum) {
		CubeByteBuffer.putint(buffer, MessageType.GETDEMO.getId());
		CubeByteBuffer.putint(buffer, demoNum);
	}
	
	/*
	SENDDEMO(MessageContext.STC, 71, new Field[] { }),//TODO incoming files
	*/
	
	public static void PutDemoPlayback(ByteBuffer buffer, boolean playing, int clientnum) {
		CubeByteBuffer.putint(buffer, MessageType.DEMOPLAYBACK.getId());
		CubeByteBuffer.putbool(buffer, playing);
		CubeByteBuffer.putint(buffer, clientnum);
	}
	
	public static void PutRecordDemo(ByteBuffer buffer, boolean record) {
		CubeByteBuffer.putint(buffer, MessageType.RECORDDEMO.getId());
		CubeByteBuffer.putbool(buffer, record);
	}
	
	public static void PutStopDemo(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.STOPDEMO.getId());
	}
	
	public static void PutClearDemos(ByteBuffer buffer, int demonum) {
		CubeByteBuffer.putint(buffer, MessageType.CLEARDEMOS.getId());
		CubeByteBuffer.putint(buffer, demonum);
	}
	
	/*
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
	*/
	
	public static void PutSayTeam(ByteBuffer buffer, String text) {
		CubeByteBuffer.putint(buffer, MessageType.SAYTEAM.getId());
		CubeByteBuffer.putstring(buffer, text);
	}
	
	/*
	CLIENT(MessageContext.STC, 85, new Field[] { }),//TODO
	*/
	
	public static void PutAuthTry(ByteBuffer buffer, String domain, String authname) {
		CubeByteBuffer.putint(buffer, MessageType.AUTHTRY.getId());
		CubeByteBuffer.putstring(buffer, domain);
		CubeByteBuffer.putstring(buffer, authname);
	}
	
	public static void PutAuthChallenge(ByteBuffer buffer, String domain, long authid, String challenge) {
		CubeByteBuffer.putint(buffer, MessageType.AUTHCHAL.getId());
		CubeByteBuffer.putstring(buffer, domain);
		CubeByteBuffer.putuint(buffer, authid);
		CubeByteBuffer.putstring(buffer, challenge);
	}
	
	public static void PutAuthAnswer(ByteBuffer buffer, String domain, long authid, String answerhash) {
		CubeByteBuffer.putint(buffer, MessageType.AUTHANS.getId());
		CubeByteBuffer.putstring(buffer, domain);
		CubeByteBuffer.putuint(buffer, authid);
		CubeByteBuffer.putstring(buffer, answerhash);
	}
	
	public static void PutRequestAuth(ByteBuffer buffer, String domain) {
		CubeByteBuffer.putint(buffer, MessageType.REQAUTH.getId());
		CubeByteBuffer.putstring(buffer, domain);
	}
	
	public static void PutPauseGame(ByteBuffer buffer, boolean pause) {
		CubeByteBuffer.putint(buffer, MessageType.PAUSEGAME.getId());
		CubeByteBuffer.putbool(buffer, pause);
	}
	
	/*
	ADDBOT(MessageContext.CTS, 91, new Field[] { }),//TODO bots
	DELBOT(MessageContext.CTS, 92, new Field[] { }),//TODO bots
	INITAI(MessageContext.STC, 93, new Field[] { }),//TODO bots
	FROMAI(MessageContext.CTS, 94, new Field[] { }),//TODO bots
	BOTLIMIT(MessageContext.CTS, 95, new Field[] { }),//TODO bots
	BOTBALANCE(MessageContext.CTS, 96, new Field[] { }),//TODO bots
	*/
	
	public static void PutMapCrc(ByteBuffer buffer, String mapName, int crc) {
		CubeByteBuffer.putint(buffer, MessageType.MAPCRC.getId());
		CubeByteBuffer.putstring(buffer, mapName);
		CubeByteBuffer.putint(buffer, crc);
	}
	
	public static void PutCheckMaps(ByteBuffer buffer) {
		CubeByteBuffer.putint(buffer, MessageType.CHECKMAPS.getId());
	}
	
	public static void PutSwitchName(ByteBuffer buffer, String name) {
		CubeByteBuffer.putint(buffer, MessageType.SWITCHNAME.getId());
		CubeByteBuffer.putstring(buffer, name);
	}
	
	public static void PutSwitchModel(ByteBuffer buffer, int playermodel) {
		CubeByteBuffer.putint(buffer, MessageType.SWITCHMODEL.getId());
		CubeByteBuffer.putint(buffer, playermodel);
	}
	
	public static void PutSwitchTeam(ByteBuffer buffer, String team) {
		CubeByteBuffer.putint(buffer, MessageType.SWITCHTEAM.getId());
		CubeByteBuffer.putstring(buffer, team);
	}
}
