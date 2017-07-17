/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author MaxK
 */
public class Formula {
       
        
        public static String expression;
        public static ArrayList opz = null;
        
        public Formula(String exp){
            expression= exp;
        }
        
        
        /// <summary>
        /// Проверка корректности скобочной структуры входного выражения 
        /// </summary>
        /// <returns>true - если все нормально, false- если есть ошибка</returns>
        /// метод бежит по входному выражению символ за символом анализирую его и считая количество скобочек. В случае возникновения
        /// ошибки возвращает false а в erposition записывает позицию на которой возникла ошибка.
        public static boolean CheckCurrency()
        {
            boolean correct = true;
            int num = 0;
            for (int i=0; i<expression.length();i++)
            {
                if (expression.charAt(i) == '(')
                {
                    num++;
                }
                else
                {
                    if (expression.charAt(i) == ')')
                    {
                        num--;
                    }
                }
                if (num < 0)
                {
                    correct = false;
                    return correct;
                }
            }
            if (num != 0)
            {
                correct = false;
            }
            return correct;
        }
        
        /// <summary>
        /// Создает  массив, в котором располагаются операторы и символы представленные в обратной польской записи (безскобочной)
        /// На этом же этапе отлавливаются почти все остальные ошибки (см код). По сути - это компиляция.
        /// </summary>
        /// <returns>массив обратной польской записи</returns>
        public static ArrayList CreateStack()
        {
            //Собственно результирующий массив
            ArrayList strarr = new ArrayList(30);
            //Стек с операторами где они временно храняться
            Stack operators = new Stack();
            String expr = expression;
            //пооператорная обработка выражения
            while (!expr.equals(""))
            {
                String op = expr.substring(0, expr.indexOf(" "));
                expr = expr.substring(expr.indexOf(" ")+1);
                switch (op)
                {
                    case "(": //1
                        {
                            //Кладем в стэк
                            operators.push(op);
                            break;
                        }
                    
                    case "*": //3
                        {
                            while (operators.size() != 0 && (operators.peek().toString().equals("*") || operators.peek().toString().equals("/") ))
                            {
                                
                                    strarr.add(operators.pop());
                                
                            }
                            operators.push(op);
                            break;
                        }
                    case "/": //3
                        {
                            while (operators.size() != 0 && (operators.peek().toString().equals("*") || operators.peek().toString().equals("/") ))
                            {
                               
                                    strarr.add(operators.pop());
                                
                            }
                            operators.push(op);
                            break;
                        }
                    
                    case "+": //2
                        {
                            while (operators.size() != 0 && (operators.peek().toString().equals("*") || operators.peek().toString().equals("/") || operators.peek().toString().equals("+") || operators.peek().toString().equals("-")))
                            {
                                    strarr.add(operators.pop());
                                
                            }
                            operators.push(op);
                            break;
                        }
                    case "-": //2
                        {
                            while (operators.size() != 0 && (operators.peek().toString().equals("*") || operators.peek().toString().equals("/") || operators.peek().toString().equals("+") || operators.peek().toString().equals("-")))
                            {
                                
                                    strarr.add(operators.pop());
                                
                            }
                            operators.push(op);
                            break;
                        }
                    case ")":
                        {
                            while (!"(".equals(operators.peek().toString()) )
                            {
                                
                                    strarr.add(operators.pop());
                            }
                            operators.pop();
                            
                            break;
                        }
                    default:
                        {
                            //на входе - число - помещаем в выходной массив
                            
                                strarr.add(op);
                            
                            break;
                        }
                }
            }
            //записываем все что осталовь в стеке в выходной массив
            while (operators.size() != 0)
            {
                strarr.add(operators.pop());
            }
            return strarr;
        }
        /// <summary>
        /// Вычисление обратной польской записи
        /// </summary>
        /// <returns>результат вычислений или сообщение об ошибке</returns>
        public static String RunEstimate()
        {
            boolean endwork = false;
            // этот цикл будет выполняться до тех пор , пока в массиве не остануться одни числа
            while (!endwork)
            {
                int i = 0;
                boolean found = false;
                //этот цикл выполняется до тех пор, пока не будет найден первый оператор
                while (i < opz.size() && !found)
                {
                    found = true;
                    
                        switch (opz.get(i).toString())
                        {

                            case "+":
                                {

                                    opz.set(i-2, Math.addExact(Integer.parseInt(opz.get(i-2).toString()), Integer.parseInt(opz.get(i-1).toString())));
                                    opz.remove(i - 1);
                                    opz.remove(i - 1);

                                    break;
                                }
                            case "-":
                                {
                                    opz.set(i-2, Math.subtractExact(Integer.parseInt(opz.get(i-2).toString()), Integer.parseInt(opz.get(i-1).toString())));
                                    opz.remove(i - 1);
                                    opz.remove(i - 1);
                                    break;
                                }
                            case "*":
                                {
                                    opz.set(i-2, Math.multiplyExact(Integer.parseInt(opz.get(i-2).toString()), Integer.parseInt(opz.get(i-1).toString())));
                                    opz.remove(i - 1);
                                    opz.remove(i - 1);
                                    break;
                                }
                            case "/":
                                {
                                    opz.set(i-2, Math.floorDiv(Integer.parseInt(opz.get(i-2).toString()), Integer.parseInt(opz.get(i-1).toString())));
                                    opz.remove(i - 1);
                                    opz.remove(i - 1);
                                    break;
                                }
                           
                            default:
                                {
                                    found = false;
                                    break;
                                }
                        }
                    
                    
                    i++;
                }
                if (found == false && i == opz.size())
                {
                    endwork = true;
                }
                
            }
            if (opz.size() != 1)
            {
                // в результате вычислений в массиве осталось несколько чисел - значит где-то ошибка в выражении, которую мы не отловили на более раннем этапе.
                
                return "Error 03";
            }
            else
            {
                return opz.get(0).toString();
            }
        }
        /// <summary>
        /// Метод, организующий вычисления. По очереди запускает CheckCorrncy, Format, CreateStack и RunEstimate
        /// </summary>
        /// <returns></returns>
        public static String Estimate()
        {
            if (CheckCurrency())
            {
               /* String  formstr = Format().toString();
                
                expression = formstr;*/
                opz=CreateStack();
                if (opz != null)
                {
                    return RunEstimate();
                }
                else
                {
                    
                    return "Error 08";
                }
            }
            else
            {
                
                return "Error 01 at ";
            }
        }
    }

