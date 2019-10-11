package com.czk.community.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * created by srdczk 2019/10/11
 */
public class StringUtil {

    private static HashSet<String> set;
    static {
        set = new HashSet<>();
        String[] a = {"javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"};
        String[] b = {"laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"};
        String[] c = {"linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "unix", "hadoop", "windows-server"};
        String[] d = {"mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql", "sqlite"};
        String[] e = {"git", "github", "visual-studio-code", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hg"};
        for (String s : a) set.add(s);
        for (String s : b) set.add(s);
        for (String s : c) set.add(s);
        for (String s : d) set.add(s);
        for (String s : e) set.add(s);
    }


    public static boolean canUse(String a) {
        String[] ss = a.split(",");
        for (String c : ss) {
            if (!set.contains(c)) return false;
        }
        return true;
    }

}
