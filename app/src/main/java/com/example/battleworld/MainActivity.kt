package com.example.battleworld

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerGame: Spinner
    private lateinit var spinnerTournament: Spinner
    private lateinit var button: Button
    private lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerGame = findViewById(R.id.spinnerGame)
        spinnerTournament = findViewById(R.id.spinnerTournament)
        button = findViewById(R.id.button)
        textViewDescription = findViewById(R.id.textViewDescription)

        val games = resources.getStringArray(R.array.game_array)
        val gameAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, games)
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGame.adapter = gameAdapter

        spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                updateTournaments(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        button.setOnClickListener {
            val gamePosition = spinnerGame.selectedItemPosition
            val tournamentPosition = spinnerTournament.selectedItemPosition
            updateDescription(gamePosition, tournamentPosition)
        }
    }

    private fun updateTournaments(gamePosition: Int) {
        val tournaments: Array<String> = when (gamePosition) {
            0 -> resources.getStringArray(R.array.game1_tournament_array)
            1 -> resources.getStringArray(R.array.game2_tournament_array)
            else -> arrayOf()
        }
        val tournamentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tournaments)
        tournamentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTournament.adapter = tournamentAdapter
    }

    private fun updateDescription(gamePosition: Int, tournamentPosition: Int) {
        val descriptions: Array<String> = when (gamePosition) {
            0 -> resources.getStringArray(R.array.game1_tournament_descriptions)
            1 -> resources.getStringArray(R.array.game2_tournament_descriptions)
            else -> arrayOf()
        }
        textViewDescription.text = Html.fromHtml(descriptions[tournamentPosition].replace("\\n", "<br/>"), Html.FROM_HTML_MODE_COMPACT)
    }
}