package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var chance = 1
    private var gameNotCompleted = false
    private var p1Wins=0
    private var p2Wins=0
    private var clickedButtons=arrayOf(
        arrayOf("", "", ""),
        arrayOf("", "", ""),
        arrayOf("", "", "")
    )

    private lateinit var player1Wins: TextView
    private lateinit var player2Wins: TextView

    private lateinit var move: TextView

    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var b4: Button
    private lateinit var b5: Button
    private lateinit var b6: Button
    private lateinit var b7: Button
    private lateinit var b8: Button
    private lateinit var b9: Button

    private lateinit var replay: Button
    private lateinit var resetScores: Button

    @SuppressLint("SetTextI18n")
    private fun resetButtons() {
        resetButton(b1)
        resetButton(b2)
        resetButton(b3)
        resetButton(b4)
        resetButton(b5)
        resetButton(b6)
        resetButton(b7)
        resetButton(b8)
        resetButton(b9)

        chance = 1
        gameNotCompleted=false
        clickedButtons=arrayOf(
            arrayOf("", "", ""),
            arrayOf("", "", ""),
            arrayOf("", "", "")
        )
        move.text="Player $chance's move"
    }

    @SuppressLint("SetTextI18n")
    private fun nextTurn() {
        chance = if (chance == 1) 2 else 1
        move.text="Player $chance's move"
    }

    private fun clickedColor(button: Button) {
        button.setBackgroundColor(Color.RED)
    }

    private fun clicked(button: Button) {
        button.text = if (chance == 1) "X" else "O"
        button.isEnabled = false
        nextTurn()
        clickedColor(button)
        gameNotCompleted = true

    }

    private fun resetButton(button: Button) {
        button.setBackgroundColor(Color.GREEN)
        button.isEnabled = true
        button.text = ""
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        when (view) {
            b1 -> {clicked(b1); clickedButtons[0][0]=if(chance==1) "O" else "X"}
            b2 -> {clicked(b2); clickedButtons[0][1]=if(chance==1) "O" else "X"}
            b3 -> {clicked(b3); clickedButtons[0][2]=if(chance==1) "O" else "X"}
            b4 -> {clicked(b4); clickedButtons[1][0]=if(chance==1) "O" else "X"}
            b5 -> {clicked(b5); clickedButtons[1][1]=if(chance==1) "O" else "X"}
            b6 -> {clicked(b6); clickedButtons[1][2]=if(chance==1) "O" else "X"}
            b7 -> {clicked(b7); clickedButtons[2][0]=if(chance==1) "O" else "X"}
            b8 -> {clicked(b8); clickedButtons[2][1]=if(chance==1) "O" else "X"}
            b9 -> {clicked(b9); clickedButtons[2][2]=if(chance==1) "O" else "X"}
            replay -> {
                if (gameNotCompleted)
                    Toast.makeText(this, "Please complete the running game first!", Toast.LENGTH_SHORT).show()
                else
                    resetButtons()
            }
            resetScores -> {
                if (gameNotCompleted)
                    Toast.makeText(this, "Please complete the running game first!", Toast.LENGTH_SHORT).show()
                else
                    AlertDialog.Builder(this).setTitle("Confirm")
                        .setMessage("Do you want to reset the scores?")
                        .setPositiveButton("Proceed") { _, _ -> resetScore(); Toast.makeText(this, "Reset successful", Toast.LENGTH_SHORT).show() }
                        .setNeutralButton("Cancel") { _, _ -> Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show() }
                        .show()
            }
        }
        if(gameNotCompleted)
            evaluateGame()
    }

    @SuppressLint("SetTextI18n")
    private fun resetScore(){
        p1Wins=0
        p2Wins=0
        player1Wins.text = "Player 1 Wins : $p1Wins"
        player2Wins.text = "Player 2 Wins : $p2Wins"
    }
    @SuppressLint("SetTextI18n")
    private fun evaluateGame(){
        if (clickedButtons[0][0]=="X" && clickedButtons[0][1]=="X" && clickedButtons[0][2]=="X" ||
            clickedButtons[1][0]=="X" && clickedButtons[1][1]=="X" && clickedButtons[1][2]=="X" ||
            clickedButtons[2][0]=="X" && clickedButtons[2][1]=="X" && clickedButtons[2][2]=="X" ||
            clickedButtons[0][0]=="X" && clickedButtons[1][0]=="X" && clickedButtons[2][0]=="X" ||
            clickedButtons[0][1]=="X" && clickedButtons[1][1]=="X" && clickedButtons[2][1]=="X" ||
            clickedButtons[0][2]=="X" && clickedButtons[1][2]=="X" && clickedButtons[2][2]=="X" ||
            clickedButtons[0][0]=="X" && clickedButtons[1][1]=="X" && clickedButtons[2][2]=="X" ||
            clickedButtons[0][2]=="X" && clickedButtons[1][1]=="X" && clickedButtons[2][0]=="X"){
            move.text="Player 1 Wins! 🎉"
            p1Wins++
            player1Wins.text="Player 1 Wins : $p1Wins"
            gameNotCompleted=false
            b1.isEnabled=false
            b2.isEnabled=false
            b3.isEnabled=false
            b4.isEnabled=false
            b5.isEnabled=false
            b6.isEnabled=false
            b7.isEnabled=false
            b8.isEnabled=false
            b9.isEnabled=false
        }
        else if (clickedButtons[0][0]=="O" && clickedButtons[0][1]=="O" && clickedButtons[0][2]=="O" ||
            clickedButtons[1][0]=="O" && clickedButtons[1][1]=="O" && clickedButtons[1][2]=="O" ||
            clickedButtons[2][0]=="O" && clickedButtons[2][1]=="O" && clickedButtons[2][2]=="O" ||
            clickedButtons[0][0]=="O" && clickedButtons[1][0]=="O" && clickedButtons[2][0]=="O" ||
            clickedButtons[0][1]=="O" && clickedButtons[1][1]=="O" && clickedButtons[2][1]=="O" ||
            clickedButtons[0][2]=="O" && clickedButtons[1][2]=="O" && clickedButtons[2][2]=="O" ||
            clickedButtons[0][0]=="O" && clickedButtons[1][1]=="O" && clickedButtons[2][2]=="O" ||
            clickedButtons[0][2]=="O" && clickedButtons[1][1]=="O" && clickedButtons[2][0]=="O"){
            move.text="Player 2 Wins! 🎉"
            p2Wins++
            player2Wins.text="Player 2 Wins : $p2Wins"
            gameNotCompleted=false
            b1.isEnabled=false
            b2.isEnabled=false
            b3.isEnabled=false
            b4.isEnabled=false
            b5.isEnabled=false
            b6.isEnabled=false
            b7.isEnabled=false
            b8.isEnabled=false
            b9.isEnabled=false
        }else {
            var t=0
            for (i in clickedButtons)
                for (j in i)
                    if (j == "") t++
            if(t==0){
                move.text="Draw!"
                gameNotCompleted=false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1Wins = findViewById(R.id.p1Win)
        player2Wins = findViewById(R.id.p2Win)

        player1Wins.text="Player 1 Wins : $p1Wins"
        player2Wins.text="Player 2 Wins : $p2Wins"


        move = findViewById(R.id.move)

        b1 = findViewById(R.id.button1)
        b2 = findViewById(R.id.button2)
        b3 = findViewById(R.id.button3)
        b4 = findViewById(R.id.button4)
        b5 = findViewById(R.id.button5)
        b6 = findViewById(R.id.button6)
        b7 = findViewById(R.id.button7)
        b8 = findViewById(R.id.button8)
        b9 = findViewById(R.id.button9)

        replay = findViewById(R.id.reset)
        resetScores = findViewById(R.id.resetScores)

        resetButtons()

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
        b9.setOnClickListener(this)

        replay.setOnClickListener(this)
        resetScores.setOnClickListener(this)
    }
}
