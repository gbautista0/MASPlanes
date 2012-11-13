/*
 * Software License Agreement (BSD License)
 *
 * Copyright (c) 2012, IIIA-CSIC, Artificial Intelligence Research Institute
 * All rights reserved.
 *
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 *
 *   Redistributions of source code must retain the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer in the documentation and/or other
 *   materials provided with the distribution.
 *
 *   Neither the name of IIIA-CSIC, Artificial Intelligence Research Institute
 *   nor the names of its contributors may be used to
 *   endorse or promote products derived from this
 *   software without specific prior written permission of
 *   IIIA-CSIC, Artificial Intelligence Research Institute
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package es.csic.iiia.planes;

import es.csic.iiia.planes.definition.DProblem;
import es.csic.iiia.planes.messaging.Message;
import es.csic.iiia.planes.messaging.MessagingAgent;
import java.util.List;

/**
 * Represents the world where a simulation runs.
 *
 * This is the object that keeps track of all the elements participating in the
 * simulation, and that synchronizes their actions.
 *
 * @author Marc Pujol <mpujol@iiia.csic.es>
 */
public interface World extends Runnable {

    /**
     * Get the factory used to build elements for this simulation.
     *
     * @return the factory.
     */
    Factory getFactory();

    /**
     * Get the list of planes in this simulation.
     *
     * @return list of the simulation's planes.
     */
    List<Plane> getPlanes();

    /**
     * Get the {@link Space} of this simulation.
     *
     * @return the {@link Space} of this simulation.
     */
    Space getSpace();

    /**
     * Get the charging station that is closest to the given location.
     *
     * @param location where an element is querying from.
     * @return the recharging station that is closest to the given location.
     */
    Station getNearestStation(Location location);

    /**
     * Add a new task to the world.
     *
     * The world keeps track of the tasks just for counting purposes.
     * {@link Plane}s must not obtain information about the tasks from here.
     * Instead, it is the {@link Operator}'s responsibility to communicate any
     * required tasks to the planes.
     *
     * @param task to add.
     */
    void addTask(Task task);

    /**
     * Remove the given task from the world.
     *
     * The world keeps track of the tasks just for counting purposes.
     * The {@link Plane}s must notify to the world that a Task has been
     * completed by calling this method.
     *
     * @param task to remove.
     */
    void removeTask(Task task);

    /**
     * Get the current simulation time (in seconds).
     *
     * @return current simulation time (in seconds).
     */
    long getTime();

    /**
     * Initialize the simulation according to the given problem definition.
     *
     * @param d problem definition.
     */
    void init(DProblem d);

    /**
     * Sends a message to all {@link MessagingAgent}s in range of the sender.
     *
     * @see Message#getSender()
     * @see MessagingAgent#getCommunicationRange()
     *
     * @param message to be sent.
     */
    public void sendMessage(Message message);

}