with open('Data\student_list.csv', 'r') as istr:
    with open('output.csv', 'w') as ostr:
        for line in istr:
            data = line.split(",")
            username = data[1].split("@")[0]
            line = data[0] + "," + data[1] + "," + username + ',' + 'password' + ',' + 'salt'
            print(line, file=ostr)