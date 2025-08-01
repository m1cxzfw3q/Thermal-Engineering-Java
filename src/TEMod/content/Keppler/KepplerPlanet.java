package TEMod.content.Keppler;

import arc.graphics.Color;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;

import mindustry.type.Planet;

import static TEMod.ThermalEngineeringCore.isComplete;
import static mindustry.content.Planets.*;

public class KepplerPlanet {
    public static Planet keppler;

    public static void load() {
        keppler = new Planet("keppler", sun, 2, 3) {{
            generator = new KepplerPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 4, 0.3f, 0.28f, 6, new Color().set(Color.valueOf("d8ecff")).mul(Color.valueOf("d8ecff")).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.8f, 0.392f, 6, Color.white.cpy().lerp(Color.valueOf("d8ecff"), 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 12;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.blue;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.enemyCoreBuildRadius = 45f * 8f;
            };
            iconColor = Color.valueOf("87c7ff");
            atmosphereColor = Color.valueOf("87c7ff");
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.18f;
            startSector = 53;
            alwaysUnlocked = true;
            landCloudColor = Color.valueOf("89d2ff");
        }};
        isComplete(KepplerPlanet.class);
    }
}
