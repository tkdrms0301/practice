from genericpath import exists
import json

with open('D:\\workspace\\sig\\app\\data_set_all.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

data_edit = list()
for diagnose in data:
    list = diagnose.keys()
    diseaseName = ''
    description = ''
    for diagnose_key in list:
        if diagnose_key != "name":
            description = description + str(diagnose[diagnose_key]) + "\n"
        else:
            diseaseName = diagnose[diagnose_key]
    diagnose_dict = dict()
    diagnose_dict['diseaseName'] = diseaseName
    diagnose_dict['description'] = description
    data_edit.append(diagnose_dict)

with open('D:\\workspace\\sig\\app\\data_set_all_edit.json', 'w', encoding='utf-8') as f:
    json.dump(data_edit, f, indent=4, ensure_ascii=False)