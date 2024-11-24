package com.example.connectfour;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

public class BoardFragment extends Fragment {

    private final String GAME_STATE = "gameState";
    private ConnectFourGame mGame;
    private GridLayout mGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        mGrid = view.findViewById(R.id.boardGrid);
        mGame = new ConnectFourGame();

        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View button = mGrid.getChildAt(i);
            button.setOnClickListener(this::onButtonClick);
        }

        return view;
    }

    private void onButtonClick(View view) {
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.COL;
        int col = buttonIndex % ConnectFourGame.COL;

        mGame.selectDisc(row, col);
        setDisc();
        // Congratulation message
        if (mGame.isGameOver()) {
            Toast.makeText(requireActivity(), "Player " + (mGame.getPlayer() == ConnectFourGame.BLUE ? "Red" : "Blue") + " wins!", Toast.LENGTH_SHORT).show();
            startGame();
        }
    }

    private void startGame() {
        mGame.newGame();
        setDisc();
    }

    private void setDisc() {
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            Button gridButton = (Button) mGrid.getChildAt(i);
            int row = i / ConnectFourGame.COL;
            int col = i % ConnectFourGame.COL;

            Drawable whiteDisc = DrawableCompat.wrap(ContextCompat.getDrawable(requireActivity(), R.drawable.circle_white));
            Drawable blueDisc = DrawableCompat.wrap(ContextCompat.getDrawable(requireActivity(), R.drawable.circle_blue));
            Drawable redDisc = DrawableCompat.wrap(ContextCompat.getDrawable(requireActivity(), R.drawable.circle_red));

            switch (mGame.getDisc(row, col)) {
                case ConnectFourGame.BLUE:
                    gridButton.setBackground(blueDisc);
                    break;
                case ConnectFourGame.RED:
                    gridButton.setBackground(redDisc);
                    break;
                default:
                    gridButton.setBackground(whiteDisc);
                    break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}
