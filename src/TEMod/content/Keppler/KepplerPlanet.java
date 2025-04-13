package TEMod.content.Keppler;

import arc.graphics.Color;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;

import static mindustry.content.Planets.*;

public class KepplerPlanet {
    public static Planet keppler;

    public static void load() {
        keppler = new Planet("keppler", sun, 2, 4) {{
            generator = new SerpuloPlanetGenerator();//等等罢先用赛普罗生成后续再写一个独立的
            meshLoader = () -> new HexMesh(keppler, 4);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.valueOf("D8ECFF")).mul(Color.valueOf("D8ECFF")).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Color.valueOf("D8ECFF"), 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 13;
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
                r.enemyCoreBuildRadius = 45f;
            };
            iconColor = Color.valueOf("87c7ff");
            atmosphereColor = Color.valueOf("87c7ff");
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.2f;
            startSector = 53;
            alwaysUnlocked = true;
            landCloudColor = Color.valueOf("89d2ff");
        }};
    }
}
