/**
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
class Solution {
    fun solution(new_id: String): String {
        var answer: String = ""
        var id = ""
        var temp = ""

        //1단계
        id = new_id.lowercase()

        //2단계
        val regex_2 = Regex("[\\w\\-\\.]+")
        var matchresult = regex_2.findAll(id)
        var listMatchResult = matchresult.toList()
        listMatchResult.forEach {
            temp += it.groupValues.get(0)
        }
        id = temp
        temp = ""

        //3단계
        val regex_3 = Regex("[\\.]+")
        temp = id.replace(regex_3, ".")
        id = temp
        temp = ""

        //4단계
        try {
            while (id.first() == '.' || id.last() == '.') {

                if (id.first() == '.')
                    id = id.replaceFirst(".", "")

                if (id.last() == '.')
                    id = id.substring(0..id.length - 2)

            }
        } catch (e: Exception) {
            //5단계
            id = "a"
        }

        //6단계
        if (id.length >= 16) id = id.substring(0..14)
        try {
            while (id.last() == '.') {

                if (id.last() == '.')
                    id = id.substring(0..id.length - 2)
            }
        } catch (e: Exception) {
            id = "a"
        }

        //7단계
        while (id.length < 3) {
            if (id.length <= 2) id = id + id.last()
        }
        answer = id

        return answer
    }
}

fun main() {
    val a = Solution()
    a.solution("...!@BaT#*..y.abcdefghijklm")
    a.solution("z-+.^.")
    a.solution("=.=")
    a.solution("123_.def")
    a.solution("abcdefghijklmn.p")
}