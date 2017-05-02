/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author movie
 */
public class Activator implements BundleActivator {
    
    public void start(BundleContext context) throws Exception {
        EnemySystem es = new EnemySystem();
        context.registerService(IGamePluginService.class.getName(), es, null);
        context.registerService(IGamePluginService.class.getName(), es, null);
    }
    
    public void stop(BundleContext context) throws Exception {
        //TODO add deactivation code here
    }
    
}
