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
public class BindStrUCase<T> implements BindMember<T> {
    
    private final BindMember bindMember;
    
    public BindStrUCase(BindMember bindMember) {
        if(bindMember instanceof BindStrUCase)
            throw new IllegalArgumentException("BindMember cannot be an instance of BindStrUCase");
        this.bindMember = bindMember;
    }

    /**
     * bind - Default implementation - Acts on Non Null Values Only
     *
     * @param target
     * @param name
     * @param v
     * @return
     */
    public boolean bind(T target, String name, Object v) {
        if (v.getClass().equals(String.class)) {
            v = ((String)v).toUpperCase();
        }
        return bindMember.bind(target, name, v);

    }
    
}
