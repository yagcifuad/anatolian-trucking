import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MapUI extends JFrame {

    private JTextField startField;
    private JTextField endField;
    private JTextArea directionsArea;
    private JButton calcRouteButton;

    public MapUI() {
        setTitle("Select Start and End Destinations");

        JPanel panel = new JPanel();

        JLabel startLabel = new JLabel("Start Destination:");
        startField = new JTextField(20);

        JLabel endLabel = new JLabel("End Destination:");
        endField = new JTextField(20);

        calcRouteButton = new JButton("Calculate Route");
        calcRouteButton.addActionListener(e -> calculateRoute());

        directionsArea = new JTextArea(20, 40);
        directionsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(directionsArea);

        panel.add(startLabel);
        panel.add(startField);
        panel.add(endLabel);
        panel.add(endField);
        panel.add(calcRouteButton);
        panel.add(scrollPane);

        add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateRoute() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("add-your-google-api-here")
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, startField.getText(), endField.getText());
        req.mode(TravelMode.DRIVING);
        try {
            DirectionsResult res = req.await();
            if (res.routes.length > 0) {
                String directions = "";
                for (int i = 0; i < res.routes[0].legs.length; i++) {
                    directions += res.routes[0].legs[i].startAddress + " to ";
                    directions += res.routes[0].legs[i].endAddress + "\n";
                    directions += res.routes[0].legs[i].distance + "\n";
                    directions += res.routes[0].legs[i].duration + "\n";
                    directions += "----------------------------------\n";
                }
                directionsArea.setText(directions);
            } else {
                directionsArea.setText("No routes found");
            }
        } catch (InterruptedException | IOException e) {
            directionsArea.setText("Error: " + e.getMessage());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new MapUI();
    }
}
