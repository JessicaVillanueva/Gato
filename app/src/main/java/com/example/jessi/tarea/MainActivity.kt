package com.example.jessi.tarea

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var turno = true
    var turnos = 0
    var mapa = Array(3,{
        Array(3, {"0"})
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gatoClick(view: View){
        //convierte el view en un boton
        val btn = (view as Button)
        //println(btn.id.toString())

        var caracter = turnos(turno)

        if (turno){
            btn.setBackgroundResource(R.color.pink)
            txt1.setText("Turno jugador = O")
        }else{
            btn.setBackgroundResource(R.color.purple)
            txt1.setText("Turno jugador = X")
        }

        btn.setText(caracter)
        btn.isEnabled = false

        when(btn.id){
            btn1.id->{mapa.get(0).set(0,caracter)}
            btn2.id->{mapa.get(0).set(1,caracter)}
            btn3.id->{mapa.get(0).set(2,caracter)}
            btn4.id->{mapa.get(1).set(0,caracter)}
            btn5.id->{mapa.get(1).set(1,caracter)}
            btn6.id->{mapa.get(1).set(2,caracter)}
            btn7.id->{mapa.get(2).set(0,caracter)}
            btn8.id->{mapa.get(2).set(1,caracter)}
            btn9.id->{mapa.get(2).set(2,caracter)}
            else->{
                println("algo anda mal xd")
            }
        }

        val win = win(caracter, mapa)

        if (win){
            print("el jugador $caracter ganó")
            showDialogAlertSimple(caracter)
        }else{
            println(win)
        }

        turno = !turno
        turnos++
        if (turnos == 9 && !win){
            showDialogAlertSimple()
        }
    }

    fun turnos(turno:Boolean): String {
        if (turno){
            return "X"
        }else{
            return "O"
        }

    }

    fun win(caracter:String, mapa:Array<Array<String>>): Boolean {

        if (mapa.get(0).get(0) == caracter && mapa.get(0).get(1) == caracter && mapa.get(0).get(2) == caracter){
            return true
        }else{
            if (mapa.get(1).get(0) == caracter && mapa.get(1).get(1) == caracter && mapa.get(1).get(2) == caracter){
                return true
            }else{
                if (mapa.get(2).get(0) == caracter && mapa.get(2).get(1) == caracter && mapa.get(2).get(2) == caracter){
                    return true
                }else{
                    if (mapa.get(0).get(0) == caracter && mapa.get(1).get(1) == caracter && mapa.get(2).get(2) == caracter){
                        return true
                    } else{
                        if (mapa.get(0).get(2) == caracter && mapa.get(1).get(1) == caracter && mapa.get(2).get(0) == caracter){
                            return true
                        }else{
                            if (mapa.get(0).get(0) == caracter && mapa.get(1).get(0) == caracter && mapa.get(2).get(0) == caracter) {
                                return true
                            } else{
                                if (mapa.get(0).get(1) == caracter && mapa.get(1).get(1) == caracter && mapa.get(2).get(1) == caracter) {
                                    return true
                                }else{
                                    if (mapa.get(0).get(2) == caracter && mapa.get(1).get(2) == caracter && mapa.get(2).get(2) == caracter){
                                        return true
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    fun showDialogAlertSimple(caracter:String) {
        AlertDialog.Builder(this)
            .setTitle("GANADOR")
            .setMessage("EL GANADOR ES EL JUGADOR $caracter.")
            .setPositiveButton("Volver a jugar",
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                    recreate()
                })
            .setCancelable(false)
            .show()
    }

    fun showDialogAlertSimple() {
        AlertDialog.Builder(this)
            .setTitle("GANADOR")
            .setMessage("EMPATE")
            .setPositiveButton("Volver a jugar",
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                    recreate()
                })
            .setCancelable(false)
            .show()
    }

}
