/*
 * Copyright (C) 2014 Angelo Mestre
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pt.isel.mpd14.probe;

import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;
import static pt.isel.mpd14.probe.util.SneakyUtils.throwAsRTException;

/**
 *
 * @author Angelo Mestre
 */
public class FormatUtils {
    
    private final Map<String, Formatter> formats = new HashMap<>();
    
    public Object getFormattedValue(AnnotatedElement m, Object v){
        Formatter aux;
        try{
            if((aux = formats.get(v.getClass().getName())) != null)
                v = aux.format(v);
            else{
                Format a = m.getAnnotation(Format.class);
                if(a != null){
                    v = a.formatter().newInstance().format(v);
                    formats.put(v.getClass().getName(), a.formatter().newInstance());
                }
                
            }
        } catch (IllegalAccessException | InstantiationException  ex) {
            throwAsRTException(ex);
        }
        return v;
    }
}
