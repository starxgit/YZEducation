import difflib
import Levenshtein
import sys

def rateOne(str1, str2):
    return difflib.SequenceMatcher(None, str1, str2).quick_ratio()

def rateTwo(str1, str2):
    return Levenshtein.ratio(str1, str2)

if __name__ == '__main__':
    parameterList=sys.argv
    arr = [];
    str1 = parameterList[1]
    str2 = parameterList[2]
    rate1 = rateOne(str1,str2)
    rate2 = rateTwo(str1,str2)
    rate = (rate1 + rate2) / 2
    print (rate)
