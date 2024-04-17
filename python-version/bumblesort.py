
def bumble_sort(a):
  swapped = True
  while swapped:
    swapped = False
    for i in range(len(a) - 1):
      if a[i] > a[i + 1]:
        a[i], a[i + 1] = a[i + 1], a[i]
        swapped = True

  return a

a=[23,43,2,7,9,3]
sorted_array=bumble_sort(a)

print("sorted arrays: ", sorted_array)
