package com.steshine.leetcode;

/**
 * Created by skychen on 2018/7/23.
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int maxLength = 0;
        int start = 0;// 2. start position
        int end = 0;// 3. end position
        StringBuffer container = new StringBuffer();// 1.a container that recode substring
        String[] arrays = s.split("");
        String reverse = "";
        for (int i = arrays.length - 1; i >= 0; i--) {
            reverse = reverse.concat(arrays[i]);
        }
        if (reverse.equals(s)) {
            return s;
        }
        for (int i = 0; i < arrays.length; i++) {
            for (int j = i; j < arrays.length; j++) {
                container.append(arrays[j]);
                if (isPalindrome(container)) {
                    if (maxLength < container.length()) {
                        maxLength = container.length();
                        start = i;
                        end = j;
                        if (start == 0 && end == arrays.length) {
                            break;
                        }
                    }
                }
            }
            container = new StringBuffer();
        }

        return subString(arrays, start, end);
    }

    private boolean isPalindrome(StringBuffer queue) {
        if (queue.length() < 2) {
            return false;
        }
        int times = queue.length() / 2;
        int head = 0;
        int tail = queue.length();
        int i = times;
        int evenCounter = 0;
        while (i > 0) {
            if (queue.substring(head, head + 1).equals(queue.substring(tail - 1, tail))) {
                evenCounter++;
            } else {
                break;
            }
            i--;
            head++;
            tail--;
        }
        return evenCounter > 0 && evenCounter == times;
    }

    private String subString(String[] array, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = start; i <= end; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }


    public String longestPalindromeReverse(String s) {
        String arrays[] = s.split("");
        String reverse = "";
        for (int i = arrays.length - 1; i >= 0; i--) {
            reverse = reverse.concat(arrays[i]);
        }
        if (reverse.equals(s)) {
            return s;
        }
        int start = 0;
        int end = 0;
        int maxLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = i + 1; j <= arrays.length; j++) {
                String tmp = s.substring(i, j);
                if (reverse.contains(tmp)) {
                    if (maxLength < (j - i)) {
                        int length = j - i;
                        if(length >=arrays.length / 2){
                            start = i;
                            end = j - 1;
                            maxLength = length;
                        }else if (!reverse.substring(i, j).equals(tmp) && isPalindrome(new StringBuffer(subString(arrays, i, j-1)))) {
                            start = i;
                            end = j - 1;
                            maxLength = length;
                        }
                    }
                }
            }
        }
        return subString(arrays, start, end);
    }


    public static void main(String[] args) {
        String source = "kztakrekvefgchersuoiuatzlmwynzjhdqqftjcqmntoyckqfawikkdrnfgbwtdpbkymvwoumurjdzygyzsbmwzpcxcdmmpwzmeibligwiiqbecxwyxigikoewwrczkanwwqukszsbjukzumzladrvjefpegyicsgctdvldetuegxwihdtitqrdmygdrsweahfrepdcudvyvrggbkthztxwicyzazjyeztytwiyybqdsczozvtegodacdokczfmwqfmyuixbeeqluqcqwxpyrkpfcdosttzooykpvdykfxulttvvwnzftndvhsvpgrgdzsvfxdtzztdiswgwxzvbpsjlizlfrlgvlnwbjwbujafjaedivvgnbgwcdbzbdbprqrflfhahsvlcekeyqueyxjfetkxpapbeejoxwxlgepmxzowldsmqllpzeymakcshfzkvyykwljeltutdmrhxcbzizihzinywggzjctzasvefcxmhnusdvlderconvaisaetcdldeveeemhugipfzbhrwidcjpfrumshbdofchpgcsbkvaexfmenpsuodatxjavoszcitjewflejjmsuvyuyrkumednsfkbgvbqxfphfqeqozcnabmtedffvzwbgbzbfydiyaevoqtfmzxaujdydtjftapkpdhnbmrylcibzuqqynvnsihmyxdcrfftkuoymzoxpnashaderlosnkxbhamkkxfhwjsyehkmblhppbyspmcwuoguptliashefdklokjpggfiixozsrlwmeksmzdcvipgkwxwynzsvxnqtchgwwadqybkguscfyrbyxudzrxacoplmcqcsmkraimfwbauvytkxdnglwfuvehpxd";
        System.out.println(new LongestPalindrome().longestPalindromeReverse(source));
        System.out.println(new LongestPalindrome().longestPalindrome(source));
    }
}
