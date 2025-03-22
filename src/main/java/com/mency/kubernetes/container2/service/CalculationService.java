package com.mency.kubernetes.container2.service;

import com.mency.kubernetes.container2.model.CalculationResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CalculationService {

    public CalculationResponse calculateSum(String filePath, String product) {
        File file = new File(filePath);

        if (!file.exists()) {
            return CalculationResponse.withError("File not found.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();
            if (header == null) {
                return CalculationResponse.withError("Input file not in CSV format.");
            }

            // Parse header to find column positions
            String[] headerColumns = header.split(",");
            int productColIndex = -1;
            int amountColIndex = -1;

            for (int i = 0; i < headerColumns.length; i++) {
                String col = headerColumns[i].trim();
                if (col.equals("product")) {
                    productColIndex = i;
                } else if (col.equals("amount")) {
                    amountColIndex = i;
                }
            }

            if (productColIndex == -1 || amountColIndex == -1) {
                return CalculationResponse.withError("Input file not in CSV format.");
            }

            // Calculate sum for the specified product
            int sum = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] columns = line.split(",");
                if (columns.length <= Math.max(productColIndex, amountColIndex)) {
                    return CalculationResponse.withError("Input file not in CSV format.");
                }

                String lineProduct = columns[productColIndex].trim();

                if (lineProduct.equals(product)) {
                    try {
                        int amount = Integer.parseInt(columns[amountColIndex].trim());
                        sum += amount;
                    } catch (NumberFormatException e) {
                        return CalculationResponse.withError("Input file not in CSV format.");
                    }
                }
            }

            return CalculationResponse.withSum(sum);

        } catch (IOException e) {
            return CalculationResponse.withError("Error reading file.");
        }
    }
}