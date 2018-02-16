package com.wukong.generator.plugins;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class WukongPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }
}
