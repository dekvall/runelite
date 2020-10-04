package net.runelite.client.plugins.groundmarkers;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;

public class GroundMarkerCountOverlay extends OverlayPanel
{
    private final GroundMarkerPlugin plugin;
    private final GroundMarkerConfig config;

    @Inject
    private GroundMarkerCountOverlay(GroundMarkerPlugin plugin, GroundMarkerConfig config)
    {
        super(plugin);
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.showTileCount())
        {
            return null;
        }

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Tiles marked:")
                .right(Integer.toString(plugin.getPoints().size()))
                .build());

        return super.render(graphics);
    }
}
