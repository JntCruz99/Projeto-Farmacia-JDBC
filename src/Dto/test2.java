/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author jonata
 */
public class test2 {
    public static void main(String[]args){
        Cliente cliente = new Cliente();
        cliente.setNome("JONATAS");

        Compra compra = new Compra(cliente); // Associa o cliente à compra
        String nomeCliente = compra.getNomeCliente();
        System.out.println(nomeCliente);
             
        }
}
