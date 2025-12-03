package Clases;

public class laliga {
	private String full_name ;
	private	String age ;
	private	String birthday ;
	private	String birthday_gmt ;
	private	String league ;
	private	String season ;
	private	String position ;
	private	String club ;
	private	String minutes_played_overall ;
	private	String minutes_played_home ;
	private	String minutes_played_away ;
	private	String nationality ;
	private	String appearances_overall ;
	private	String appearances_home ;
	private	String appearances_away ;
	private	String goals_overall ;
	private	String goals_home ;
	private	String goals_away ;
	private	String assists_overall ;
	private	String assists_home ;
	private	String assists_away ;
	private	String penalty_goals ;
	private	String penalty_misses ;
	private	String clean_sheets_overall ;
	private	String clean_sheets_home ;
	private	String clean_sheets_away ;
	private	String conceded_overall ;
	private	String conceded_home ;
	private	String conceded_away ;
	private	String yellow_cards_overall ;
	private	String red_cards_overall ;
	private	String goals_involved_per_90_overall ;
	private	String assists_per_90_overall ;
	private	String goals_per_90_overall ;
	private	String goals_per_90_home ;
	private	String goals_per_90_away ;
	private	String min_per_goal_overall ;
	private	String conceded_per_90_overall ;
	private	String min_per_conceded_overall ;
	private	String min_per_match ;
	private	String min_per_card_overall ;
	private	String min_per_assist_overall ;
	private	String cards_per_90_overall ;
	private	String rank_in_league_top_attackers ;
	private	String rank_in_league_top_midfielders ;
	private	String rank_in_league_top_defenders ;
	private	String rank_in_club_top_scorer;
	public laliga(String full_name, String age, String birthday, String birthday_gmt, String league, String season,
			String position, String club, String minutes_played_overall, String minutes_played_home,
			String minutes_played_away, String nationality, String appearances_overall, String appearances_home,
			String appearances_away, String goals_overall, String goals_home, String goals_away, String assists_overall,
			String assists_home, String assists_away, String penalty_goals, String penalty_misses,
			String clean_sheets_overall, String clean_sheets_home, String clean_sheets_away, String conceded_overall,
			String conceded_home, String conceded_away, String yellow_cards_overall, String red_cards_overall,
			String goals_involved_per_90_overall, String assists_per_90_overall, String goals_per_90_overall,
			String goals_per_90_home, String goals_per_90_away, String min_per_goal_overall,
			String conceded_per_90_overall, String min_per_conceded_overall, String min_per_match,
			String min_per_card_overall, String min_per_assist_overall, String cards_per_90_overall,
			String rank_in_league_top_attackers, String rank_in_league_top_midfielders,
			String rank_in_league_top_defenders, String rank_in_club_top_scorer) {
		super();
		this.full_name = full_name;
		this.age = age;
		this.birthday = birthday;
		this.birthday_gmt = birthday_gmt;
		this.league = league;
		this.season = season;
		this.position = position;
		this.club = club;
		this.minutes_played_overall = minutes_played_overall;
		this.minutes_played_home = minutes_played_home;
		this.minutes_played_away = minutes_played_away;
		this.nationality = nationality;
		this.appearances_overall = appearances_overall;
		this.appearances_home = appearances_home;
		this.appearances_away = appearances_away;
		this.goals_overall = goals_overall;
		this.goals_home = goals_home;
		this.goals_away = goals_away;
		this.assists_overall = assists_overall;
		this.assists_home = assists_home;
		this.assists_away = assists_away;
		this.penalty_goals = penalty_goals;
		this.penalty_misses = penalty_misses;
		this.clean_sheets_overall = clean_sheets_overall;
		this.clean_sheets_home = clean_sheets_home;
		this.clean_sheets_away = clean_sheets_away;
		this.conceded_overall = conceded_overall;
		this.conceded_home = conceded_home;
		this.conceded_away = conceded_away;
		this.yellow_cards_overall = yellow_cards_overall;
		this.red_cards_overall = red_cards_overall;
		this.goals_involved_per_90_overall = goals_involved_per_90_overall;
		this.assists_per_90_overall = assists_per_90_overall;
		this.goals_per_90_overall = goals_per_90_overall;
		this.goals_per_90_home = goals_per_90_home;
		this.goals_per_90_away = goals_per_90_away;
		this.min_per_goal_overall = min_per_goal_overall;
		this.conceded_per_90_overall = conceded_per_90_overall;
		this.min_per_conceded_overall = min_per_conceded_overall;
		this.min_per_match = min_per_match;
		this.min_per_card_overall = min_per_card_overall;
		this.min_per_assist_overall = min_per_assist_overall;
		this.cards_per_90_overall = cards_per_90_overall;
		this.rank_in_league_top_attackers = rank_in_league_top_attackers;
		this.rank_in_league_top_midfielders = rank_in_league_top_midfielders;
		this.rank_in_league_top_defenders = rank_in_league_top_defenders;
		this.rank_in_club_top_scorer = rank_in_club_top_scorer;
	}
	public String getclub() {
		return club;
	}
	public void setclub(String club) {
		club = club;
	}
	public String getfull_name() {
		return full_name;
	}
	public String getage() {
		return age;
	}
	public String getbirthday() {
		return birthday;
	}
	public String getbirthday_gmt() {
		return birthday_gmt;
	}
	public String getleague() {
		return league;
	}
	public String getseason() {
		return season;
	}
	public String getposition() {
		return position;
	}
	public String getminutes_played_overall() {
		return minutes_played_overall;
	}
	public String getminutes_played_home() {
		return minutes_played_home;
	}
	public String getminutes_played_away() {
		return minutes_played_away;
	}
	public String getnationality() {
		return nationality;
	}
	public String getappearances_overall() {
		return appearances_overall;
	}
	public String getappearances_home() {
		return appearances_home;
	}
	public String getappearances_away() {
		return appearances_away;
	}
	public String getgoals_overall() {
		return goals_overall;
	}
	public String getgoals_home() {
		return goals_home;
	}
	public String getgoals_away() {
		return goals_away;
	}
	public String getassists_overall() {
		return assists_overall;
	}
	public String getassists_home() {
		return assists_home;
	}
	public String getassists_away() {
		return assists_away;
	}
	public String getpenalty_goals() {
		return penalty_goals;
	}
	public String getpenalty_misses() {
		return penalty_misses;
	}
	public String getclean_sheets_overall() {
		return clean_sheets_overall;
	}
	public String getclean_sheets_home() {
		return clean_sheets_home;
	}
	public String getclean_sheets_away() {
		return clean_sheets_away;
	}
	public String getconceded_overall() {
		return conceded_overall;
	}
	public String getconceded_home() {
		return conceded_home;
	}
	public String getconceded_away() {
		return conceded_away;
	}
	public String getyellow_cards_overall() {
		return yellow_cards_overall;
	}
	public String getred_cards_overall() {
		return red_cards_overall;
	}
	public String getgoals_involved_per_90_overall() {
		return goals_involved_per_90_overall;
	}
	public String getassists_per_90_overall() {
		return assists_per_90_overall;
	}
	public String getgoals_per_90_overall() {
		return goals_per_90_overall;
	}
	public String getgoals_per_90_home() {
		return goals_per_90_home;
	}
	public String getgoals_per_90_away() {
		return goals_per_90_away;
	}
	public String getmin_per_goal_overall() {
		return min_per_goal_overall;
	}
	public String getconceded_per_90_overall() {
		return conceded_per_90_overall;
	}
	public String getmin_per_conceded_overall() {
		return min_per_conceded_overall;
	}
	public String getmin_per_match() {
		return min_per_match;
	}
	public String getmin_per_card_overall() {
		return min_per_card_overall;
	}
	public String getmin_per_assist_overall() {
		return min_per_assist_overall;
	}
	public String getcards_per_90_overall() {
		return cards_per_90_overall;
	}
	public String getrank_in_league_top_attackers() {
		return rank_in_league_top_attackers;
	}
	public String getrank_in_league_top_midfielders() {
		return rank_in_league_top_midfielders;
	}
	public String getrank_in_league_top_defenders() {
		return rank_in_league_top_defenders;
	}
	public String getrank_in_club_top_scorer() {
		return rank_in_club_top_scorer;
	}

}
