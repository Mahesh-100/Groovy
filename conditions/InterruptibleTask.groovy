package com.conditions

class InterruptibleTask implements Runnable {
    @Override
    void run() {

        try {
            println Thread.currentThread().name
            while (true) {
                println "Working..."
                Thread.sleep(500)

                if (Thread.currentThread().isInterrupted()) {
                    println "Thread was interrupted. Exiting..."
                    break
                }
            }

        } catch (InterruptedException e) {
            println "Thread was interrupted during sleep. Exiting..."
        }
    }
    static void main(String... args){
        Thread t = new Thread(new InterruptibleTask())
        println Thread.currentThread().name
        t.start()
        Thread.sleep(2000)
        println "Interrupting the thread..."
        t.interrupt()
        t.join()
        println "Main thread exiting."

    }
}

