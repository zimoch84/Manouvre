/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.network.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @author Piotr
 */
public class FakeClient 
{

    public static void main(String argv[]) {
    // Make two pairs of connected piped streams
    PipedInputStream pinc = null;
    PipedInputStream pins = null;
    PipedOutputStream poutc = null;
    PipedOutputStream pouts = null;
    
    try {
      pinc = new PipedInputStream();
      pins = new PipedInputStream();
      poutc = new PipedOutputStream(pins);
      pouts = new PipedOutputStream(pinc);
    }
    catch (IOException e) {
      System.out.println(
        "PipedStreamExample: Failed to build piped streams.");
      System.exit(1);
    }

    // Make the client and server threads, connected by the streams
    PipedClient pc = new PipedClient(pinc, poutc);
    PipedServer ps = new PipedServer(pins, pouts);

    // Start the threads
    System.out.println("Starting server...");
    ps.start();
    System.out.println("Starting client...");
    pc.start();

    // Wait for threads to end
    try {
      ps.join();
      pc.join();
    }
    catch (InterruptedException e) {}

    System.exit(0);
  }
}
class PipedClient extends Thread
{
  PipedInputStream pin;
  PipedOutputStream pout;

  public PipedClient(PipedInputStream in, PipedOutputStream out)
  {
    pin = in;
    pout = out;
  }

  public void run()
  {
    // Wrap a data stream around the input and output streams
    DataInputStream din = new DataInputStream(pin);
    DataOutputStream dout = new DataOutputStream(pout);

    // Say hello to the server...
    try
      {
        System.out.println("PipedClient: Writing greeting to server...");
        dout.writeChars("hello from PipedClient\n");
      }
    catch (IOException e)
      {
        System.out.println("PipedClient: Couldn't get response.");
        System.exit(1);
      }

    // See if it says hello back...
    try
      {
        System.out.println("PipedClient: Reading response from server...");
        String response = din.readLine();
        System.out.println("PipedClient: Server said: \"" 
                           + response + "\"");
      }
    catch (IOException e)
      {
        System.out.println("PipedClient: Failed to connect to peer.");
      }

    stop();
  }

}
    
class PipedServer extends Thread
{
  PipedInputStream pin;
  PipedOutputStream pout;

  public PipedServer(PipedInputStream in, PipedOutputStream out)
  {
    pin = in;
    pout = out;
  }

  public void run()
  {
    // Wrap a data stream around the input and output streams
    DataInputStream din = new DataInputStream(pin);
    DataOutputStream dout = new DataOutputStream(pout);

    // Wait for the client to say hello...
    try
      {
        System.out.println("PipedServer: Reading from client...");
        String clientHello = din.readLine();
        System.out.println("PipedServer: Client said: \""
                           + clientHello + "\"");
      }
    catch (IOException e)
      {
        System.out.println("PipedServer: Couldn't get hello from client.");
        stop();
      }

    // ...and say hello back.
    try
      {
        System.out.println("PipedServer: Writing response to client...");
        dout.writeChars("hello I am the server.\n");
      }
    catch (IOException e)
      {
        System.out.println("PipedServer: Failed to connect to client.");
      }
    stop();
  }
}  
    


    

