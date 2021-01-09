package me.superbiebel.defaultoffenseprocessor;

import me.lucko.helper.Schedulers;
import me.lucko.helper.scheduler.Scheduler;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingCacheProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingDataHandlerProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingDatabaseProvider;
import me.superbiebel.offenseprocessingdataabstraction.providers.OffenseProcessingProvider;
import me.superbiebel.punishmentmanager.PunishmentManager;
import me.superbiebel.punishmentmanager.offenseprocessing.OffenseProcessor;
import me.superbiebel.punishmentmanager.offenseprocessing.OffenseProcessorFactory;

public class DefaultOffenseProcessorFactory implements OffenseProcessorFactory {
    @Override
    public OffenseProcessor giveOffenseExecutor() throws Exception {
        return new DefaultOffenseProcessor();
    }

    @Override
    public void init() throws Exception {
        OffenseProcessingProvider offenseProcessingCacheProvider = new OffenseProcessingCacheProvider();
        OffenseProcessingProvider offenseProcessingDatabaseProvider = new OffenseProcessingDatabaseProvider();
        OffenseProcessingProvider offenseProcessingDatahandlerProvider = new OffenseProcessingDataHandlerProvider();

        Scheduler scheduler = Schedulers.async();

        scheduler.callLater(()->{
            offenseProcessingCacheProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonecachedriver")); //TODO: fill in class path
            return null;
        },60);
        scheduler.callLater(()->{
            offenseProcessingDatabaseProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonedatabasedriver")); //TODO: fill in class path
            return null;
        },60);
        scheduler.callLater(()->{
            offenseProcessingDatahandlerProvider.init(PunishmentManager.giveConfig().getString("reflectionpath.standalonedatahandlerdriver")); //TODO: fill in class path
            return null;
        },60);
    }

}
