package com.wang.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MaxWang on 2018/9/20.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/20 16:05
 * 修改人  ：MaxWang
 * 修改时间：2018/9/20
 * 修改备注：
 */
//线程安全的单例   初始化信息
public class Configurator {
    //HanshMap 配置的键值对
    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();
    //放置所有的icons
    private static  final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //初始化的时候配置未完成
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }
    //通过内部类获取Configurator  的单例对象
    static Configurator getInstence(){
        return Holder.INSTANCE;
    }
    //获取存放 初始化信息的键值对
    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    //初始化完成
    public final void configure(){
        initIcons();//初始化的时候加入图标
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    /**
     * 初始化host地址
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    //字体图标库
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    /**
     * 初始化Icon
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }


    /**
     * 检查是否配置完成
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
