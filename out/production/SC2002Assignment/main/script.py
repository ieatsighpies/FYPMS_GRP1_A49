with open('Data\project_record.csv', 'r') as istr1:
    with open('output.csv', 'w') as ostr:
        for line1 in istr1:
            data1 = line1.split(",")
            with open('Data\\faculty_list.csv', 'r') as istr2:
                for line2 in istr2:
                    data2 = line2.split(",")
                    if(data1[0] == data2[0]):
                            line = data1[0]+ ","+data2[2]+ ","+data1[1]
                            print(line, file=ostr, end='')