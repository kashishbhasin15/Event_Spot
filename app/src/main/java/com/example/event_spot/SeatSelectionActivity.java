package com.example.event_spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SeatSelectionActivity extends AppCompatActivity {
    private GridLayout seatGrid;
    private int totalRows = 5;
    private int totalColumns = 8;
    private int[][] seatStatus;

    AppCompatButton bookTicketsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seat_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        seatGrid = findViewById(R.id.seatGrid);
        seatStatus = new int[totalRows][totalColumns];  // Initialize seat statuses
        generateSeats();

        bookTicketsButton=findViewById(R.id.bookTicketsButton);
        bookTicketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SeatSelectionActivity.this,TicketActivity.class);
                startActivity(intent);
            }
        });
    }

    private void generateSeats() {
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalColumns; col++) {
                AppCompatButton seatButton = new AppCompatButton(this);

                // Set seat initial appearance (pink for available)
                seatButton.setBackgroundResource(R.drawable.seat_empty_icon);
                seatButton.setLayoutParams(new GridLayout.LayoutParams());
                seatButton.setLayoutParams(new GridLayout.LayoutParams(
                        new ViewGroup.LayoutParams(100, 100)));  // Define the size of seat buttons

                final int r = row;
                final int c = col;

                // Handle seat click events
                seatButton.setOnClickListener(v -> {
                    // Toggle seat selection
                    if (seatStatus[r][c] == 0) { // Available (empty seat)
                        seatStatus[r][c] = 2; // Mark as selected
                        seatButton.setBackgroundResource(R.drawable.seat_select_icon);
                    } else if (seatStatus[r][c] == 2) { // If already selected
                        seatStatus[r][c] = 0; // Deselect
                        seatButton.setBackgroundResource(R.drawable.seat_empty_icon);
                    }
                    updateSelectedSeats();  // Update the selected seats details below
                });

                // Add the button to GridLayout
                seatGrid.addView(seatButton);
            }
        }
    }

    private void updateSelectedSeats() {
        StringBuilder selectedSeats = new StringBuilder("Selected Seats: ");
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalColumns; col++) {
                if (seatStatus[row][col] == 2) {
                    selectedSeats.append("Seat ").append((row * totalColumns) + col + 1).append(", ");
                }
            }
        }
        // Remove last comma and space
        if (selectedSeats.length() > 15) {
            selectedSeats.setLength(selectedSeats.length() - 2);
        } else {
            selectedSeats.append("None");
        }

        // Update TextView with selected seat numbers
        TextView seatDetailsTextView = findViewById(R.id.section_seat);
        seatDetailsTextView.setText(selectedSeats.toString());
    }
}