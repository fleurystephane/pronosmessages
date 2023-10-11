package com.yosfl.common.utils;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.NotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class CommonUtility {
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String TECNICAL_USER = "tecnical@example.com";

    public static String formatDateTime(@NotNull LocalDateTime date){
        return date.format(CUSTOM_FORMATTER);
    }
}
