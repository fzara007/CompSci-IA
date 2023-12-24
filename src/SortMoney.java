import java.util.ArrayList;

public class SortMoney  {
  private static HotelEvent[] temp;
  private static int type;

  // Sorts a[0], ..., a[a.length-1] in ascending order
  //   using the Mergesort algorithm.
  public static void sort(ArrayList<HotelEvent> a, int t)
  {
    int n = a.size();
    temp = new HotelEvent[n];
    type = t;
    recursiveSort(a, 0, n-1);
  }

  // Recursive helper method: sorts a.get(from), ..., a.get(to)
  private static void recursiveSort(ArrayList<HotelEvent> a, int from, int to)
  {
    if (to - from < 2)       // Base case: 1 or 2 elements
    {
      if (to > from && a.get(to).getData()[type] < a.get(from).getData()[type])
      {
        // swap a.get(to) and a.get(from)
        HotelEvent aTemp = a.get(to); 
        a.set(to, a.get(from)); 
        a.set(from, aTemp);
      }
    }
    else                     // Recursive case
    {
      int middle = (from + to) / 2;
      recursiveSort(a, from, middle);
      recursiveSort(a, middle + 1, to);
      merge(a, from, middle, to);
    }
  }

  // Merges a.get(from) ... a[middle] and a[middle+1] ... a.get(to)
  //   into one sorted array a.get(from) ... a.get(to)
  private static void merge(ArrayList<HotelEvent> a, int from, int middle, int to)
  {
    int i = from, j = middle + 1, k = from;

    // While both arrays have elements left unprocessed:
    while (i <= middle && j <= to)
    {
      if (a.get(i).getData()[type] < a.get(j).getData()[type])
      {
        temp[k] = a.get(i);   // Or simply temp[k] = a[i++];
        i++;
      }
      else
      {
        temp[k] = a.get(j);
        j++;
      }
      k++;
    }

    // Copy the tail of the first half, if any, into temp:
    while (i <= middle)
    {
      temp[k] = a.get(i);     // Or simply temp[k++] = a[i++]
      i++;
      k++;
    }

    // Copy the tail of the second half, if any, into temp:
    while (j <= to)
    {
      temp[k] = a.get(j);     // Or simply temp[k++] = a[j++]
      j++;
      k++;
    }

    // Copy temp back into a
    for (k = from; k <= to; k++)
      a.set(k, temp[k]);
  }
}
