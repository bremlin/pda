package pda.objects;

public enum ItemType {
    Project     ("projectRow"),
    Wbs         ("wbs"),
    Step        ("stepRow"),
    Activity    ("activityRow"),
    Code        ("codeRow"),
    CodeTwo     ("codeTwoRow"),
    CodeThree   ("codeThreeRow");

    private final String style;

    ItemType(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
