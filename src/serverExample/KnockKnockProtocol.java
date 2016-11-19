package serverExample;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    // ints to indicate state

    private static final int NUMJOKES = 5;
    //int to show how many jokes KKP class has

    private int state = WAITING;
    // initializes the KKP's state to WAITING => 0
    private int currentJoke = 0;
    // tracker for the current joke

    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    // all the responses to "Who's there?"
    private String[] answers = { "Turnip the heat, it's cold in here!",
            "I didn't know you could yodel!",
            "Bless you!",
            "Is there an owl in here?",
            "Is there an echo in here?" };
    // all the punchlines

    public String processInput(String theInput) {
        // takes in a message from the client (theInput), outputs a message to respond with
        String theOutput = null;    //to be returned at end

        if (state == WAITING) {
            theOutput = "Knock! Knock!";    // initial message
            state = SENTKNOCKKNOCK; // update state
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                // if the client had input "Who's there?"
                theOutput = clues[currentJoke];
                // return next joke setup (response to "Who's there?")
                state = SENTCLUE;
                // update state to indicate clue was sent
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                        "Try again. Knock! Knock!";
                //if the client didn't put "Who's there?", return error message/retry
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                // if the client responded correctly (ex - "Turnip who?")
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                // deliver punchline and ask if the client wants another joke
                state = ANOTHER;
                // set state to indicate the server asked if the client wants another
            } else {
                // if response to setup is wrong
                theOutput = "You're supposed to say \"" +
                        clues[currentJoke] +
                        " who?\"" +
                        "! Try again. Knock! Knock!";
                // return statement asking them to try again
                state = SENTKNOCKKNOCK;
                // reset state back to after "Knock! Knock!"
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                // if client indicates they want another joke
                theOutput = "Knock! Knock!";
                // restart process
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                // put currentJoke indicator back to 0 if at end of list
                else
                    currentJoke++;
                // increment currentJoke indicator to go to next one
                state = SENTKNOCKKNOCK;
                // reset state back to after "Knock! Knock!"
            } else {
                // if client didn't want another joke
                theOutput = "Bye.";
                // output "Bye."
                state = WAITING;
                // set state back to the initial WAITING
            }
        }
        return theOutput;
    }
}
