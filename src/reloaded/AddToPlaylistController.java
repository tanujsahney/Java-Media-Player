package reloaded;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AddToPlaylistController {

    FXMLDocumentController controller = new FXMLDocumentController();

    @FXML
    TextField searchBar;

    @FXML
    ImageView star1;

    @FXML
    ImageView star2;

    @FXML
    ImageView star3;

    @FXML
    ImageView star4;

    @FXML
    ImageView star5;

    @FXML
    ListView playlistList;

    @FXML
    Button play;

    @FXML
    Button pause;

    @FXML
    Slider slider;


    public void showRating(String songName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/media";
        Connection connect = DriverManager.getConnection(url, "root", "utkarshsql");
        String query= "SELECT Rating from MainTable  WHERE Song_Name='" + songName +"'";
        PreparedStatement pre = connect.prepareStatement(query);
        ResultSet result = pre.executeQuery();
        Image ratedImage=new Image("\\images\\icons8-star-64.png");
        while (result.next()) {
            int rating = result.getInt("Rating");
            switch (rating){
                case 5:
                    star5.setImage(ratedImage);
                case 4:
                    star4.setImage(ratedImage);
                case 3:
                    star3.setImage(ratedImage);
                case 2:
                    star2.setImage(ratedImage);
                case 1:
                    star1.setImage(ratedImage);
            }

        }


    }

    @FXML
    public void setRating(MouseEvent event) throws FileNotFoundException, ClassNotFoundException, SQLException {
        Image unratedImage=new Image("\\images\\icons8-add-to-favorites-64.png");
        star1.setImage(unratedImage);
        star2.setImage(unratedImage);
        star3.setImage(unratedImage);
        star4.setImage(unratedImage);
        star5.setImage(unratedImage);
        ImageView star = (ImageView) event.getSource();
        String starID= star.getId();
        Image ratedImage=new Image("\\images\\icons8-star-64.png");

        switch (starID){
            case "star5":
                star5.setImage(ratedImage);
            case "star4":
                star4.setImage(ratedImage);
            case "star3":
                star3.setImage(ratedImage);
            case "star2":
                star2.setImage(ratedImage);
            case "star1":
                star1.setImage(ratedImage);
        }
        int starRating=starID.charAt(starID.length()-1)-'0';
        System.out.println(starRating);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/media";
        Connection connect = DriverManager.getConnection(url, "root", "utkarshsql");

        String query = "UPDATE MainTable set Rating  ='"+starRating +"' WHERE Song_Name='" + FXMLDocumentController.songName +"'";
        Statement statement=connect.createStatement();
        statement.executeUpdate(query);
    }


    @FXML
    public void addNewPlaylistButton(ActionEvent event) throws IOException {
        controller.addNewPlaylist();
    }

    @FXML
    public void addNewPlaylistImage(MouseEvent event) throws IOException {
        controller.addNewPlaylist();
    }

    public void setPlaylists() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url2 = "jdbc:mysql://localhost:3306/media";
        Connection connect = DriverManager.getConnection(url2, "root", "utkarshsql");


        String query2 = "SELECT Playlist FROM MainTable where Playlist is not NULL GROUP BY Playlist ORDER BY Playlist;";
        PreparedStatement pre = connect.prepareStatement(query2);
        ResultSet result = pre.executeQuery();
        while (result.next()) {
            String playlist = result.getString("Playlist");
            playlistList.getItems().add(playlist);
        }
        boolean musicState=controller.getStatus();
        play.setDisable(musicState);
        pause.setDisable(!musicState);
        play.setVisible(!musicState);
        pause.setVisible(musicState);
    }


    public void addPlaylist(String text) {
        playlistList.getItems().add(text);

    }

    @FXML
    public void addToPlaylistButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.addToPlaylistButton(event);
    }

    @FXML
    public void addToPlaylistImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.addToPlaylistImageButton(event);
    }

    @FXML
    public void searchButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.transferToSearch(searchBar.getText(),event);
    }

    @FXML
    public void homeButton(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        controller.homeButton(event);
    }
    @FXML
    public void myMusicButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.myMusicButton(event);
    }

    @FXML
    public void myMusicImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.myMusicImageButton(event);
    }

    @FXML
    public void artistButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.artistButton(event);
    }

    @FXML
    public void artistImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.artistImageButton(event);
    }

    @FXML
    public void albumButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.albumButton(event);
    }
    @FXML
    public void albumImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.albumImageButton(event);
    }

    @FXML
    public void genreButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.genreButton(event);
    }
    @FXML
    public void genreImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.genreImageButton(event);
    }

    @FXML
    public void myVideosButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.myVideosButton(event);
    }

    @FXML
    public void myVideosImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.myVideosImageButton(event);
    }

    @FXML
    public void playlistsButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.playlistsButton(event);
    }

    @FXML
    public void playlistsImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.playlistsImageButton(event);
    }

    @FXML
    public void mostPlayedButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.mostPlayedButton(event);
    }

    @FXML
    public void mostPlayedImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.mostPlayedImageButton(event);
    }

    @FXML
    public void recentPlaysButton(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.recentPlaysButton(event);
    }

    @FXML
    public void recentPlaysImageButton(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        controller.recentPlaysImageButton(event);
    }

    public void setVolume() {
        if(FXMLDocumentController.mediaPlayer!=null) {
            slider.setValue(FXMLDocumentController.mediaPlayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    FXMLDocumentController.mediaPlayer.setVolume(slider.getValue() / 100);
                }
            });
        }
    }
}
