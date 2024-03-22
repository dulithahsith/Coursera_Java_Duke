from scipy.optimize import curve_fit
import numpy as np

# Given data
miles = np.array([21.7852, 27.4646, 27.4708, 30.0309])
fare = np.array([126.83, 137.5, 137.51, 142.32])

# Define the fare calculation function


def fare_function(miles, x, y, z):
    return x + (10) * y + (miles - 20) * z


# Initial guess for parameters
initial_guess = [1, 1, 1]

# Fit the curve to find the optimal values of x, y, and z, with constraints on x
popt, _ = curve_fit(fare_function, miles, fare, bounds=(
    [0, 0.5, 0.5], [60, 6, 5]))

# Extracting the optimal values of x, y, and z
x, y, z = popt

print("Optimal value of x:", x)
print("Optimal value of y:", y)
print("Optimal value of z:", z)

# Calculate the fares using the optimal parameters
calculated_fares = fare_function(miles, x, y, z)

# Print the calculated fares for the given miles
print("\nCalculated fares for the given miles:")
for i in range(len(miles)):
    print(f"For {miles[i]} miles, the fare is {calculated_fares[i]}")
