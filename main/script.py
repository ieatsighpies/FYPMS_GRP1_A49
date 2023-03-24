with open('Data\project_record.csv', 'r') as istr1:
    with open('output.csv', 'w') as ostr:
        for line1 in istr1:
            data1 = line1.split(",")
            with open('Data\\faculty_list.csv', 'r') as istr2:
                for line2 in istr2:
                    data2 = line2.split(",")
                    if(data1[1] == data2[0]):
                            line = ",".join([data1[0],data2[0], data2[1], data1[3].rstrip(),"AVAILABLE,NaN,NaN"])
                            line = line.rstrip()  # remove the newline character at the end
                            print(line, file=ostr)