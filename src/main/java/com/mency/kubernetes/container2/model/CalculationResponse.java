package com.mency.kubernetes.container2.model;

public class CalculationResponse {
    private Integer sum;
    private String error;

    // Constructors
    public static CalculationResponse withSum(int sum) {
        CalculationResponse response = new CalculationResponse();
        response.setSum(sum);
        return response;
    }

    public static CalculationResponse withError(String error) {
        CalculationResponse response = new CalculationResponse();
        response.setError(error);
        return response;
    }

    // Getters and Setters
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}