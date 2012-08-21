package org.cxsbs.core.command;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	/** The string which is used to issue the command. */
    String name();
    
    /** A description of what the command does. */
    String description();
    
    /**
    Group clearances provides a simplified way of providing sane default group permissions
    without maintaining settings regarding exactly which group can do which command.

    A clearance is a property of a UserGroup which establishes their base line permissions.

    4 being admin level functionality
    3 being trusted user functionality
    2 being standard user functionality
    1 being public/non-user functionality
    */
    int minimumGroupClearance();
    
    /**
    Minimum visible permissions allows it to be indicated that commands should, by default, only be executable
    when the user has a certain level of Sauerbraten visible permissions.

    In the vanilla Sauerbraten server many of the functionalities are only available when a player has master/admin.
    This is good as it provides a consistent feeling and permits players to choose matches which are being moderated by
    reasonable moderators.

    Though invisible privileges have their place, they should not be used for standard game moderation. It is annoying
    and disconcerting to players when they have no sense of who might change the map or teams.
    */
    int minimumVisisblePermissions();
}
