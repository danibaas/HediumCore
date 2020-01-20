package nl.shizleshizle.hediumcore.permissions;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PermAttachments {
    private static HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();

    public static void addPerms(User p) {
        PermissionAttachment pa = p.addAttachment(Main.p);

        if (Perm.hasPerm(p, PermGroup.LEAD_DEVELOPER)) {
            ArrayList<String> perms = new ArrayList<>();
            perms.addAll(Permissions.getPermissions(PermGroup.LEAD_DEVELOPER));
            perms.addAll(Permissions.getPermissions(PermGroup.ADMIN));
            perms.addAll(Permissions.getPermissions(PermGroup.MODERATOR));
            perms.addAll(Permissions.getPermissions(PermGroup.RANKED));
            perms.addAll(Permissions.getPermissions(PermGroup.MEMBER));
            pa.setPermission(new Permission("bukkit.command.tps"), true);
            pa.setPermission(new Permission("minecraft.command.save-all"), true);
            pa.setPermission(new Permission("bukkit.command.plugins"), true);
            pa.setPermission(new Permission("bukkit.command.timings"), true);
            pa.setPermission(new Permission("bukkit.command.version"), true);
            pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
            pa.setPermission(new Permission("worldedit.*"), true);
            pa.setPermission(new Permission("worldguard.*"), true);
            for (String permission : perms) {
                pa.setPermission(new Permission(permission), true);
            }
        }
        if (Perm.hasPerm(p, PermGroup.ADMIN)) {
            ArrayList<String> perms = new ArrayList<>();
            perms.addAll(Permissions.getPermissions(PermGroup.ADMIN));
            perms.addAll(Permissions.getPermissions(PermGroup.MODERATOR));
            perms.addAll(Permissions.getPermissions(PermGroup.RANKED));
            perms.addAll(Permissions.getPermissions(PermGroup.MEMBER));
            for (String permission : perms) {
                pa.setPermission(new Permission(permission), true);
            }
            pa.setPermission(new Permission("bukkit.command.tps"), true);
            pa.setPermission(new Permission("minecraft.command.save-all"), true);
            pa.setPermission(new Permission("bukkit.command.plugins"), true);
            pa.setPermission(new Permission("bukkit.command.timings"), true);
            pa.setPermission(new Permission("bukkit.command.version"), true);
            pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
            pa.setPermission(new Permission("worldedit.*"), true);
            pa.setPermission(new Permission("worldguard.*"), true);
        }
        if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
            ArrayList<String> perms = new ArrayList<>();
            perms.addAll(Permissions.getPermissions(PermGroup.MODERATOR));
            perms.addAll(Permissions.getPermissions(PermGroup.RANKED));
            perms.addAll(Permissions.getPermissions(PermGroup.MEMBER));
            for (String permission : perms) {
                pa.setPermission(new Permission(permission), true);
            }
            pa.setPermission(new Permission("bukkit.command.tps"), true);
            pa.setPermission(new Permission("bukkit.command.plugins"), true);
            pa.setPermission(new Permission("bukkit.command.version"), true);
            pa.setPermission(new Permission("worldedit.*"), true);
            pa.setPermission(new Permission("worldguard.*"), true);
        }
        if (Perm.hasPerm(p, PermGroup.RANKED)) {
            ArrayList<String> perms = new ArrayList<>();
            perms.addAll(Permissions.getPermissions(PermGroup.RANKED));
            perms.addAll(Permissions.getPermissions(PermGroup.MEMBER));
            for (String permission : perms) {
                pa.setPermission(new Permission(permission), true);
            }
        }
        if (Perm.hasPerm(p, PermGroup.MEMBER)) {
            ArrayList<String> perms = new ArrayList<>(Permissions.getPermissions(PermGroup.MEMBER));
            for (String permission : perms) {
                pa.setPermission(new Permission(permission), true);
            }
        }
        permissions.put(p.getUniqueId(), pa);
    }

    public static void removePerms(User p) {
        if (permissions.containsKey(p.getUniqueId())) {
            p.removeAttachment(permissions.get(p.getUniqueId()));
            permissions.remove(p.getUniqueId());
        }
    }
}
