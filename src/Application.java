/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CalcClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author MaxK
 */
public class Application {
    public static void main(String[] args) throws IOException{
        Formula calc;
        System.out.println("Введите выражение вставляя пробел после каждого оператора. Например: \"1 + ( 2 - 3 ) * ( 11 + 2 * ( 3 + 4 ) ) + 5 \" ");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        calc = new Formula(reader.readLine());
        System.out.println(calc.Estimate());
    }
}
