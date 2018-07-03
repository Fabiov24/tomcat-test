/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import com.datastax.driver.core.*;
import com.datastax.driver.core.Cluster.Builder;
import java.util.*;
import redis.clients.jedis.Jedis;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fab
 */
public class CONEXIONBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
    }

    public void VerComentarios() {
        try {
            Jedis jedis = new Jedis("localhost");
            Set<String> usuarios = jedis.keys("*");

            usuarios.forEach((usuario) -> {
                System.out.println(usuario + ":");

                List<String> value2 = jedis.lrange(usuario, 0, -1);
                value2.forEach((comentario) -> {
                    //cada comentario
                    System.out.println("\t" + comentario);

                });

            });

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Comentar(String usuario, String comentario) {

        try {
            Jedis jedis = new Jedis("localhost");
            jedis.rpush(usuario, comentario);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
