package android.example.waterapp;

import java.util.Comparator;

public class SortedPatient implements Comparator<Patient>
{
    @Override
    public int compare(Patient o1, Patient o2) {
        return o2.getDehydrationState().compareTo(o1.getDehydrationState());
    }
}