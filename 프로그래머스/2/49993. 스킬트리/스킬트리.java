class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String user_skill:skill_trees){
            String chk = "";
            for (char s:user_skill.toCharArray()) {
                for (int i = 0;i<skill.length();i++) {
                    if (s == skill.charAt(i)) {
                        chk = chk + s;
                    }
                }
            }
            if(skill.startsWith(chk)) {
                answer=answer+1;
            }

        }
        return answer;
    }
}