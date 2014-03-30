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

/**
 *
 * @author Angelo Mestre
 */
public class BindNonNull implements BindMember {
    private final BindMember m;
    
    public BindNonNull(BindMember m){
        this.m = m;
    }

    @Override
    public <T> boolean bind(T target, String name, Object v) {
        if(v == null)
            return false;
        
        return m.bind(target, name, v);

    }
    
}
